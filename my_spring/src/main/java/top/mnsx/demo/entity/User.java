package top.mnsx.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.demo.entity
 * @CreateTime: 2022/9/1
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;
    private Double balance;
}
