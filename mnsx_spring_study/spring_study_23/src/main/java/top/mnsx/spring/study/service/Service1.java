package top.mnsx.spring.study.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Lazy
public class Service1 {
}
