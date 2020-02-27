package cn.edu.hust.nlp.controller;

import cn.edu.hust.nlp.entity.Story;
import cn.edu.hust.nlp.service.StoryService;
import cn.edu.hust.nlp.vo.Result;
import cn.edu.hust.nlp.vo.ResultTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/story")
public class StoryController {

    @Autowired
    StoryService storyService;

    @ResponseBody
    @GetMapping("create")
    public Result create() {
        //to do
        return Result.success();
    }

    @ResponseBody
    @GetMapping("save")
    public Result save(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "title") String title, @RequestParam(value = "text") String text, @RequestParam(value = "type") String type, @RequestParam(value = "publik") Boolean publik) {
        Story story = new Story();
        story.setUid(uid);
        story.setTitle(title);
        story.setText(text);
        story.setDate(new Date());
        story.setType(type);
        story.setPublik(publik);

        boolean isSuccess = storyService.save(story);
        if (isSuccess) return Result.success();
        else return Result.error(ResultTypeEnum.SERVICE_ERROR);

    }

    @ResponseBody
    @GetMapping("list")
    public Result list(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        IPage<Story> iPage = new Page<>(page, rows);
        List<Story> storyList = storyService.page(iPage, queryWrapper).getRecords();

        if (storyList == null || storyList.size() == 0) return Result.error(ResultTypeEnum.NULL_RESULT);
        else return Result.success(storyList);
    }
}
