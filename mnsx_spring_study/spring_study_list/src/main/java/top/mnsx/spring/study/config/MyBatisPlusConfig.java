package top.mnsx.spring.study.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("top.mnsx.spring.study.mapper")
public class MyBatisPlusConfig {
}
