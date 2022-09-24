package top.mnsx.demo.controller;

import top.mnsx.demo.entity.User;
import top.mnsx.demo.service.UserService;
import top.mnsx.my_spring.annotation.Autowired;
import top.mnsx.my_spring.annotation.bean.Controller;
import top.mnsx.my_spring.mvc.annotation.CrossOrigin;
import top.mnsx.my_spring.mvc.annotation.RequestMapping;
import top.mnsx.my_spring.mvc.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.demo.controller
 * @CreateTime: 2022/9/1
 * @Description:
 */
@RequestMapping("/users")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> findAll() {
        List<User> users = userService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        response.put("data", users);
        return response;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> addUser(String name, String password, Double balance) {
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        if (userService.addUser(name, password, balance)) {
            response.put("message", "新建成功");
        } else {
            response.put("message", "新建失败");
        }
        return response;
    }


    @RequestMapping("/removeUser")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> removeUser(Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        if (userService.removeUser(id)) {
            response.put("message", "删除成功");
        } else {
            response.put("message", "删除失败");
        }
        return response;
    }

    @RequestMapping("/findUser")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> findUser(Integer id) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.findUser(id);
        if (user != null) {
            response.put("state", HttpServletResponse.SC_OK);
            response.put("data", user);
        }
        return response;
    }

    @RequestMapping("/modifyUser")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> modifyUser(Integer id, String name, String password, Double balance) {
        Map<String, Object> response = new HashMap<>();
        response.put("state", HttpServletResponse.SC_OK);
        if (userService.modifyUser(id, name, password, balance)) {
            response.put("message", "修改成功");
        } else {
            response.put("message", "修改失败");
        }
        return response;
    }
}
