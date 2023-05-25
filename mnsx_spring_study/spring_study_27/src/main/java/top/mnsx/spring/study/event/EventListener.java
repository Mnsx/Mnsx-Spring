package top.mnsx.spring.study.event;

public interface EventListener<E extends AbstractEvent> {
    void onEvent(E event);
}
