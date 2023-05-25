package top.mnsx.spring.study.config;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import top.mnsx.spring.study.annotation.EnableProxy;
import top.mnsx.spring.study.domain.ProxyMode;

//@Configuration
public class ProxyConfiguration implements ImportAware {

    private AnnotationAttributes info;

//    @Bean
    public ProxyMode proxyMode() {
        ProxyMode proxyMode = new ProxyMode();
        proxyMode.setMode(info.getString("mode"));
        return proxyMode;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
       this.info = AnnotationAttributes.fromMap(
               importMetadata.getAnnotationAttributes(EnableProxy.class.getName(), false)
       );
    }
}
