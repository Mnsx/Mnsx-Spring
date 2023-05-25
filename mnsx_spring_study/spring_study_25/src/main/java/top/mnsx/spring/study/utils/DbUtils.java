package top.mnsx.spring.study.utils;

import java.util.HashMap;
import java.util.Map;

public class DbUtils {
    public static Map<String, Object> getMailInfoFromDb() {
        Map<String, Object> result = new HashMap<>();
        result.put("mail.host", "gmail.com");
        result.put("mail.username", "mnsx");
        return result;
    }
}
