package cn.edu.hust.nlp.controller;

import cn.edu.hust.nlp.entity.Collection;
import cn.edu.hust.nlp.entity.Dianzan;
import cn.edu.hust.nlp.entity.Story;
import cn.edu.hust.nlp.service.CollectionService;
import cn.edu.hust.nlp.service.DianzanService;
import cn.edu.hust.nlp.service.StoryService;
import cn.edu.hust.nlp.vo.Result;
import cn.edu.hust.nlp.vo.ResultTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/dianzan")
public class DianzanController {

    @Autowired
    DianzanService dianzanService;

    @Autowired
    StoryService storyService;

    @ResponseBody
    @GetMapping("dianzan")
    public Result dianzan(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "sid") Integer sid) {
        Dianzan dianzan = new Dianzan();
        dianzan.setUid(uid);
        dianzan.setSid(sid);

        boolean isSuccess = dianzanService.save(dianzan);
        if (isSuccess) {
            Story story = storyService.getById(sid);
            story.setAcclaim(story.getAcclaim() + 1);
            storyService.updateById(story);
            return Result.success();
        } else return Result.error();
    }

    @ResponseBody
    @GetMapping("isDianzan")
    public Result isDianzan(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "sid") Integer sid) {
        QueryWrapper<Dianzan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("sid", sid);
        Dianzan dianzan = dianzanService.getOne(queryWrapper);
        if (dianzan != null) return Result.success("yes");
        else return Result.success("no");
    }

    @ResponseBody
    @GetMapping("delete")
    public Result delete(@RequestParam(value = "id") Integer id) {
        Dianzan dianzan = dianzanService.getById(id);
        boolean isSuccess = dianzanService.removeById(id);
        if (isSuccess) {
            Story story = storyService.getById(dianzan.getSid());
            story.setAcclaim(story.getAcclaim() - 1);
            storyService.updateById(story);
            return Result.success();
        } else return Result.error();
    }

    @ResponseBody
    @GetMapping("myList")
    public Result myList(@RequestParam(value = "uid") Integer uid) {
        QueryWrapper<Dianzan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        List<Dianzan> dianzanList = dianzanService.list(queryWrapper);
        if (dianzanList == null || dianzanList.size() == 0) return Result.error(ResultTypeEnum.NULL_RESULT);
        else return Result.success(dianzanList);
    }
}
