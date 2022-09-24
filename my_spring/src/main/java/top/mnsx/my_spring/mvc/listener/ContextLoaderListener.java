package top.mnsx.my_spring.mvc.listener;

import top.mnsx.my_spring.container.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.listener
 * @CreateTime: 2022/7/24
 * @Description:
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String contextConfigLocation = sce.getServletContext().getInitParameter("contextConfigLocation");
        contextConfigLocation = contextConfigLocation.split(":")[1];
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap(contextConfigLocation);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
