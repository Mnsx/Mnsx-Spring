package top.mnsx.spring_mnsx.container.generator;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 18:12
 * @Description: 获取annotation包中所有的类
 */
@SuppressWarnings("unchecked")
public class AnnotationGenerator {
    /**
     * 获取annotation.component包中的所有类，并加入到list中
     * @return 返回list集合
     */
    public static List<Class<? extends Annotation>> getAnnotationList() {
        List<Class<? extends Annotation>> annotationList = new ArrayList<>();

        try {
            Enumeration<URL> annotationUrls = AnnotationGenerator.class.getClassLoader().getResources("top/mnsx/spring_mnsx/annotation/component");
            while (annotationUrls.hasMoreElements()) {
                URL url = annotationUrls.nextElement();
                File annotationFile = new File(url.getPath());
                File[] files = annotationFile.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String fileName = file.getPath();
                        int index = fileName.indexOf("classes" + File.separator);
                        fileName = fileName.substring(index + 8, fileName.length() - 6);
                        fileName = fileName.replace(File.separator, ".");
                        Class<?> aClass = Class.forName(fileName);
                        annotationList.add((Class<? extends Annotation>) aClass);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return annotationList;
    }
}
