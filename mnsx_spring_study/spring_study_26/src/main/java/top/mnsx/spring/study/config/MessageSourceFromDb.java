package top.mnsx.spring.study.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

public class MessageSourceFromDb extends StaticMessageSource implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        this.addMessage("desc", Locale.CHINA, "从db中来");
        this.addMessage("desc", Locale.UK, "from db");
    }
}
