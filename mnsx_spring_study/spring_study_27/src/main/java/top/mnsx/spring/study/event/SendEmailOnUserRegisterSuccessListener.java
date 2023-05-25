package top.mnsx.spring.study.event;

import org.springframework.stereotype.Component;

@Component
public class SendEmailOnUserRegisterSuccessListener implements EventListener<UserRegisterSuccessEvent> {
    @Override
    public void onEvent(UserRegisterSuccessEvent event) {
        System.out.println("发送邮件-用户" + event.getUsername() + "创建成功");
    }
}
