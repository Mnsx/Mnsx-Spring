package top.mnsx.spring_mnsx.container.parser;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 14:44
 * @Description: 将XML文件转换成Spring配置
 */
public class XmlSpringConfigParser {
    /**
     * 获取XML文件中配置的包扫描路径
     * @param springConfig XML文件路径
     * @return 返回包扫描路径
     */
    public static String getBasePackage(String springConfig) {
        // 包扫描路径
        String basePackage = "";

        // 引入dom4j XML文件解析依赖
        SAXReader reader = new SAXReader();
        // 读取指定位置下的XML文件，通过nio
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(springConfig);
        
        // 操作XML，获取包扫描路径
        try {
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element element = rootElement.element("component-scan");
            Attribute attribute = element.attribute("base-package");
            basePackage = attribute.getText();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return basePackage;
    }

    public static Map<String, String> getDataSource(String springConfig) {
        // 将数据库相关配置存放到map中
        Map<String, String> result = new HashMap<>();

        // 解析XML
        SAXReader reader = new SAXReader();
        InputStream inputstream = Thread.currentThread().getContextClassLoader().getResourceAsStream(springConfig);
        try {
            Document document = reader.read(inputstream);
            Element rootElement = document.getRootElement();
            Element datasource = rootElement.element("datasource");
            Iterator<Element> properties = datasource.elementIterator("property");
            while (properties.hasNext()) {
                Element property = properties.next();
                // 将name作结键，value作为值
                result.put(property.attributeValue("name"), property.attributeValue("value"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return result;
    }
}
