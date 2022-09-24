package top.mnsx.my_spring.parser;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.parser
 * @CreateTime: 2022/8/2
 * @Description:
 */
public class SpringMvcParser {

    public static List<String> getStaticResources(String configLocation) {
        List<String> resources = new ArrayList<>();
        InputStream inputStream = null;
        try {
            SAXReader reader =new SAXReader();
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configLocation);
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements("resources");
            if (elements != null) {
                for (Element element : elements) {
                    String location = element.attribute("location").getText();
                    resources.add(location);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
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
