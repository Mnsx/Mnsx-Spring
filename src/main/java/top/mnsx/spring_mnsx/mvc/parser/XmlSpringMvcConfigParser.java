package top.mnsx.spring_mnsx.mvc.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:24
 * @Description: MVC XML配置文件解析器
 */
public class XmlSpringMvcConfigParser {
    public static List<String> getStaticResources(String configLocation) {
        // 存放资源路径
        List<String> resources = new ArrayList<>();
        InputStream inputStream = null;
        try {
            SAXReader reader = new SAXReader();
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configLocation);
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            // 解析资源路径
            List<Element> elements = rootElement.elements("resources");
            if (elements != null) {
                for (Element element : elements) {
                    // 获取location存放进入集合中
                    String location = element.attribute("location").getText();
                    resources.add(location);
                }
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resources;
    }
}
