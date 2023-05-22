package top.mnsx.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.mnsx.spring.study.domain.User1;
import top.mnsx.spring.study.domain.User2;
import top.mnsx.spring.study.mapper.User2Mapper;

import javax.annotation.Resource;

@Service
public class User2Service {
    @Resource
    private User2Mapper user2Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2Mapper.insert(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredException(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2Mapper.insert(user2);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2Mapper.insert(user2);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewException(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2Mapper.insert(user2);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2Mapper.insert(user2);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedException(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2Mapper.insert(user2);
        throw new RuntimeException();
    }
}
