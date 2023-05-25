package top.mnsx.spring.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import top.mnsx.spring.study.config.MailConfig;
import top.mnsx.spring.study.config.MainConfig1;
import top.mnsx.spring.study.config.MainConfig2;
import top.mnsx.spring.study.config.ScopeConfig;
import top.mnsx.spring.study.domain.User;
import top.mnsx.spring.study.domain.User1;
import top.mnsx.spring.study.domain.User2;
import top.mnsx.spring.study.bean.MyScopeBean;
import top.mnsx.spring.study.refresh.BeanRefreshScope;
import top.mnsx.spring.study.refresh.MainConfig;
import top.mnsx.spring.study.refresh.RefreshConfigUtil;
import top.mnsx.spring.study.refresh.TestService;
import top.mnsx.spring.study.utils.DbUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SpringStudyTest {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        Map<String, Object> map = DbUtils.getMailInfoFromDb();
        MapPropertySource source = new MapPropertySource("mail", map);
        context.getEnvironment().getPropertySources().addFirst(source);

        context.register(MainConfig1.class);
        context.refresh();
        MailConfig mailConfig = context.getBean(MailConfig.class);
        System.out.println(mailConfig);
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ScopeConfig.class);
        context.refresh();

        User user = context.getBean(User.class);
        System.out.println("创建Bean: " + user);

        for (int i = 0; i < 3; ++i) {
            System.out.println(i + ">>>" + user.getName());
        }
    }

    @Test
    public void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(); context.getBeanFactory().registerScope(BeanRefreshScope.SCOPE_REFRESH, BeanRefreshScope.getInstance());
        context.register(MainConfig.class);

        RefreshConfigUtil.refreshPropertySouce(context);
        context.refresh();

        TestService service = context.getBean(TestService.class);
        System.out.println("配置更新前——");
        for (int i = 0; i < 3; i++) {
            System.out.println(service);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("配置更新后——");
        for (int i = 0; i < 3; i++) {
            RefreshConfigUtil.updateDbConfig(context);
            System.out.println(service);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
