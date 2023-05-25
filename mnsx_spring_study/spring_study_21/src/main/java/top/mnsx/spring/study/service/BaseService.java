package top.mnsx.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import top.mnsx.spring.study.dao.IDao;

public class BaseService<T> {
    @Autowired
    private IDao<T> dao;

    public IDao<T> getDao() {
        return dao;
    }

    public void setDao(IDao<T> dao) {
        this.dao = dao;
    }
}
