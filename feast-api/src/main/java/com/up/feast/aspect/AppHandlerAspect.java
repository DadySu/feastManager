package com.up.feast.aspect;

import base.BaseResponse;
import com.up.feast.controller.base.AppCoreHandler;
import common.utils.ApiErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * description: 接口实现相关  切面
 * 作用：验证请求参数 + 格式化输出参数
 *
 * @author liub
 * @date 4/15/18 17:56
 */
@Slf4j
@Aspect
@Component
public class AppHandlerAspect extends AppCoreHandler {

    @Pointcut("execution(public * com.up.feast.controller.handler..*.*(String,Class))")
    public void apiHandler() {
        log.info("AOP_HANDLER");
    }

    @Around("apiHandler()")
    public BaseResponse doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] objs = joinPoint.getArgs();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + method);
        log.info("ARGS : " + Arrays.toString(objs));

        checkArgs(joinPoint, method, objs);
        String requestBody = String.valueOf(objs[0]);
        Class clazz = (Class) objs[1];

        BaseResponse baseResponse = processBefore(requestBody, clazz);
        if (ApiErrorEnum.SUCCESS.getRetCode().equals(baseResponse.getRetCode())) {
            objs[0] = baseResponse.getBody();
            baseResponse = (BaseResponse) joinPoint.proceed(objs);
        }
        return processAfter(baseResponse);

    }

    /**
     * 验证方法参数列表
     *
     * @param joinPoint
     * @param method
     * @param objs
     * @throws Exception
     */
    private void checkArgs(ProceedingJoinPoint joinPoint, String method, Object[] objs) throws Exception {
        int arrNum = joinPoint.getArgs().length;
        if (arrNum < 2) {
            throw new Exception("AOP验证异常，参数列表格式有误，args(String,Class,...) => " +
                    "args(请求参数，验证类.class,...)");
        }
        if (!(objs[0] instanceof String)) {
            throw new Exception("AOP验证异常，" + method + "第一参数需要String 类型");
        }
        if (!(objs[1] instanceof Class)) {
            throw new Exception("AOP验证异常，" + method + "第二参数需要Class 类型");
        }
    }

}
