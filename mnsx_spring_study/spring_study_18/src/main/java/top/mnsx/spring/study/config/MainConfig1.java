package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Import;
import top.mnsx.spring.study.service.Service1;
import top.mnsx.spring.study.service.Service2;

@Import({Service1.class, Service2.class})
public class MainConfig1 {
}
