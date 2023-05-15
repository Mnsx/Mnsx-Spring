package top.mnsx.demo;

import top.mnsx.spring_mnsx.annotation.component.Component;
import top.mnsx.spring_mnsx.annotation.component.Repository;
import top.mnsx.spring_mnsx.annotation.injection.Autowired;
import top.mnsx.spring_mnsx.annotation.transaction.Transactional;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 22:24
 * @Description:
 */
@Repository
public class TestRepository {
    public void test() {
        System.out.println("do Repository");
    }
}
