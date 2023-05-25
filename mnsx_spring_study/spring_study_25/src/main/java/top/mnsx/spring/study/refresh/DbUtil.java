package top.mnsx.spring.study.refresh;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DbUtil {
    public static Map<String, Object> getInfoFromDb() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("test.name", UUID.randomUUID());
        return result;
    }
}
