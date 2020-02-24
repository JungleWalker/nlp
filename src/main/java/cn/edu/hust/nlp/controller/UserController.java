package cn.edu.hust.nlp.controller;

import cn.edu.hust.nlp.entity.User;
import cn.edu.hust.nlp.service.UserService;
import cn.edu.hust.nlp.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     前端控制器
 * </p>
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("register")
    public Result register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "sex") Character sex) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);

        boolean isSuccess = userService.save(user);

        return Result.success(isSuccess);
    }
}
