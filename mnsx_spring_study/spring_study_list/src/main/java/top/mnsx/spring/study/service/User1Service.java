package top.mnsx.spring.study.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.mnsx.spring.study.domain.User1;
import top.mnsx.spring.study.mapper.User1Mapper;

import javax.annotation.Resource;

@Service
public class User1Service {
    @Resource
    private User1Mapper user1Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(String name) {
        User1 user1 = new User1();
        user1.setName(name);
        user1Mapper.insert(user1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew(String name) {
        User1 user1 = new User1();
        user1.setName(name);
        user1Mapper.insert(user1);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested(String name) {
        User1 user1 = new User1();
        user1.setName(name);
        user1Mapper.insert(user1);
    }
}
