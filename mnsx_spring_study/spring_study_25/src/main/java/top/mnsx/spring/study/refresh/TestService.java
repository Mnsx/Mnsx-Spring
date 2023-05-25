package top.mnsx.spring.study.refresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestService {
    @Autowired
    private TestConfig testConfig;

    @Override
    public String toString() {
        return "TestService{" +
                "testConfig=" + testConfig +
                '}';
    }
}
