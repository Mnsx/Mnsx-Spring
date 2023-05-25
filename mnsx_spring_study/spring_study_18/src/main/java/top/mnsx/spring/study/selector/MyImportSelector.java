package top.mnsx.spring.study.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import top.mnsx.spring.study.config.ModuleConfig;
import top.mnsx.spring.study.service.Service1;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                Service1.class.getName(),
                ModuleConfig.class.getName()
        };
    }
}
