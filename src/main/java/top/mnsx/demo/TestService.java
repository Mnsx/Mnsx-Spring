package top.mnsx.demo;

import top.mnsx.spring_mnsx.annotation.component.Service;
import top.mnsx.spring_mnsx.annotation.injection.Autowired;
import top.mnsx.spring_mnsx.annotation.transaction.Transactional;

import java.io.Serializable;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 22:23
 * @Description:
 */
@Service("test")
@Transactional
public class TestService {
    @Autowired
    private TestRepository repository;

    public void test() {
        repository.test();
        System.out.println("do Service");
    }
}
