package top.mnsx.spring.study.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import top.mnsx.spring.study.processor.MethodCostTimeProxyBeanPostProcessor;

public class MethodCostTimeImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                MethodCostTimeProxyBeanPostProcessor.class.getName()
        };
    }
}
