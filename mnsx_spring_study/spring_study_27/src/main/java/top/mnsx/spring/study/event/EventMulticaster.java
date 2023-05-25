package top.mnsx.spring.study.event;

public interface EventMulticaster {
    /**
     * 广播事件给所有的监听器，对该事件感兴趣的监听器会处理该事件
     * @param event 事件
     */
    void multicastEvent(AbstractEvent event);

    /**
     * 添加一个事件监听器（监听器中包含了监听器能处理那些事件
     * @param listener 监听器
     */
    void addEventListener(EventListener<?> listener);

    /**
     * 删除一个事件监听器
     * @param listener 监听器
     */
    void removeEventListener(EventListener<?> listener);
}
