package top.mnsx.spring_mnsx.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:48
 * @Description: 处理器映射
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerMapping {
    private Object controlObject;
    private Method method;
}
