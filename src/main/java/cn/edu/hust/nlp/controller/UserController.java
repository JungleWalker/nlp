package cn.edu.hust.nlp.controller;

import cn.edu.hust.nlp.entity.User;
import cn.edu.hust.nlp.service.UserService;
import cn.edu.hust.nlp.vo.Result;
import cn.edu.hust.nlp.vo.ResultTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User one = userService.getOne(queryWrapper);
        if (one != null) return Result.error(ResultTypeEnum.EXIST_RESULT);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);

        boolean isSuccess = userService.save(user);
        if (isSuccess) return Result.success();
        else return Result.error(ResultTypeEnum.SERVICE_ERROR);

    }

    @ResponseBody
    @GetMapping("login")
    public Result login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpSession session) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        User user = userService.getOne(queryWrapper);
        if (user == null) return Result.error(ResultTypeEnum.NULL_RESULT);
        else {
            session.setAttribute("user", user);
            return Result.success();
        }
    }

    public Result logout(HttpSession session) {
        session.removeAttribute("user");
        return Result.success();
    }
}
