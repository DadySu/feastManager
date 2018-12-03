package common.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @description:
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */
public class SpringBean implements ServletContextListener {

    private static WebApplicationContext springContext = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        if (springContext == null) {
            springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public static Object getBean(String name) {
        return springContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return springContext.getBean(clazz);
    }
}
