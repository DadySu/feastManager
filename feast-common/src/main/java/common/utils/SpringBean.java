package common.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 8/2/18 12:33
 * @Version 1.0
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
