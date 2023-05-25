package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Import;
import top.mnsx.spring.study.selector.MyImportSelector;

@Import({MyImportSelector.class})
public class MainConfig5 {
}
