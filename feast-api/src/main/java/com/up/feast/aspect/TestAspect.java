package com.up.feast.aspect;

import com.up.feast.annotation.TestAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Description TestAnnotation注解的切面
 *
 * @author liub
 * @date 2019-01-30 16:18
 */
@Component
@Aspect
@Slf4j
public class TestAspect {

    /**
     * Validate annotation.
     */
    @Pointcut("@annotation(com.up.feast.annotation.TestAnnotation)")
    public void testAnnotation() {
    }

    /**
     * Do before.
     */
    @Before("testAnnotation()")
    public void doBefore() {
    }

    /**
     * Do after.
     *
     * @param joinPoint the join point
     */
    @AfterReturning(pointcut = "testAnnotation()")
    public void doAfter(final JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        //得到拦截的方法
        Method method = getMethodByClassAndName(target.getClass(), methodName);
        Object[] objects = joinPoint.getArgs();
        //方法的参数
        assert method != null;
        TestAnnotation annotation = (TestAnnotation) getAnnotationByMethod(method, TestAnnotation.class);
        if (annotation != null) {
            log.info(annotation.address());
            log.info(annotation.condition().name());
        }
    }

    /**
     * 根据目标方法和注解类型  得到该目标方法的指定注解
     */
    private Annotation getAnnotationByMethod(Method method, Class annoClass) {
        Annotation[] all = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    /**
     * 根据类和方法名得到方法
     */
    private Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }
}

