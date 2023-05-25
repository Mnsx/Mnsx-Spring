package top.mnsx.spring.study.event;

public class UserRegisterService {

    private EventMulticaster eventMulticaster;

    public void registerUser(String username) {
        System.out.printf("用户注册[%s]成功%n", username);
        this.eventMulticaster.multicastEvent(new UserRegisterSuccessEvent(this, username));
    }

    public EventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }

    public void setEventMulticaster(EventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }
}
