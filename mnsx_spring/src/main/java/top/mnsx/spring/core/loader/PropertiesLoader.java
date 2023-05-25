package top.mnsx.spring.core.loader;

import top.mnsx.spring.core.domain.BeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 解析properties文件的解析
 */
public class PropertiesLoader {
    /**
     * 获取properties文件中资源
     * @return Map
     */
    public Map<String, BeanDefinition> loadSource() {
        HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();
        Properties properties = new Properties();
        // 解析properties文件
        try {
            InputStream inputStream = PropertiesLoader.class.getResourceAsStream("/bean.properties");
            properties.load(inputStream);
            for (String beanName : properties.stringPropertyNames()) {
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanName(beanName);
                String className = properties.getProperty(beanName);
                Class<?> beanClass = Class.forName(className);
                beanDefinition.setBeanClass(beanClass);
                beanDefinitionMap.put(beanName, beanDefinition);
            }
            // 回收资源
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanDefinitionMap;
    }
}
