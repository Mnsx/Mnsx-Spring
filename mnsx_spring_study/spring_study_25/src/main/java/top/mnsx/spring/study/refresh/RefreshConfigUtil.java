package top.mnsx.spring.study.refresh;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

public class RefreshConfigUtil {
    public static void updateDbConfig(AbstractApplicationContext context) {
        refreshPropertySouce(context);
        BeanRefreshScope.clean();
    }

    public static void refreshPropertySouce(AbstractApplicationContext context) {
        Map<String, Object> infoFromDb = DbUtil.getInfoFromDb();
        MapPropertySource mapPropertySource = new MapPropertySource("test", infoFromDb);
        context.getEnvironment().getPropertySources().addFirst(mapPropertySource);
    }
}
