package top.mnsx.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired
    private User1Service user1Service;
    @Autowired
    private User2Service user2Service;

    // required
    public void noTransactionExceptionRequiredRequired() {
        user1Service.required("zs");
        user2Service.required("ls");
        throw new RuntimeException();
    }

    public void noTransactionRequiredRequiredException() {
        user1Service.required("zs");
        user2Service.requiredException("ls");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequired() {
       user1Service.required("zs");
       user2Service.required("ls");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiredException() {
        user1Service.required("zs");
        user2Service.requiredException("ls");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequired() {
        user1Service.required("zs");
        user2Service.required("ls");
        throw new RuntimeException();
    }

    // requires_new
    public void noTransactionRequiresNewRequiresNew() {
        user1Service.requiresNew("zs");
        user2Service.requiresNew("ls");
    }

    public void noTransactionRequiresNewRequiresNewException() {
        user1Service.requiresNew("zs");
        user2Service.requiresNewException("ls");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequiresNewRequiresNew() {
        user1Service.required("zs");
        user1Service.requiresNew("ls");
        user2Service.requiresNew("ww");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewException() {
        user1Service.required("zs");
        user1Service.requiresNew("ls");
        user2Service.requiresNewException("ww");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewExceptionCatch() {
        user1Service.required("zs");
        user1Service.requiresNew("ls");
        try {
            user2Service.requiresNewException("ww");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void noTransactionExceptionNestedNested() {
        user1Service.nested("zs");
        user2Service.nested("ls");
        throw new RuntimeException();
    }

    public void noTransactionNestedNestedException() {
        user1Service.nested("zs");
        user2Service.nestedException("ls");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void transactionExceptionNestedNested() {
        user1Service.nested("zs");
        user2Service.nested("ls");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void transactionNestedNestedException() {
        user1Service.nested("zs");
        user2Service.nestedException("ls");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void transactionNestedNestedExceptionCatch() {
        user1Service.nested("zs");
        try {
            user2Service.nestedException("ls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
