package top.mnsx.spring_mnsx.mvc.listener;

import top.mnsx.spring_mnsx.container.ClassPathXmlApplication;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 14:50
 * @Description: 监听
 */
public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
        contextConfigLocation = contextConfigLocation.split(":")[1];
        ClassPathXmlApplication context = ClassPathXmlApplication.getContext();
        context.bootStrap(contextConfigLocation);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
