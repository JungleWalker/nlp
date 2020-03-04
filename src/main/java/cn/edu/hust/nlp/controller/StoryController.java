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
        story.setAcclaim(0);
        story.setCollections(0);

        boolean isSuccess = storyService.save(story);
        if (isSuccess) return Result.success();
        else return Result.error();

    }

    @ResponseBody
    @GetMapping("getOne")
    public Result getOne(Integer id) {
        Story story = storyService.getById(id);

        if (story != null) return Result.success(story);
        else return Result.error(ResultTypeEnum.NULL_RESULT);
    }

    /**
     * 根据用户id分页查询故事
     * @param uid
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @GetMapping("myList")
    public Result myList(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        IPage<Story> iPage = new Page<>(page, rows);
        List<Story> storyList = storyService.page(iPage, queryWrapper).getRecords();

        if (storyList == null || storyList.size() == 0) return Result.error(ResultTypeEnum.NULL_RESULT);
        else return Result.success(storyList);
    }

    /**
     * 根据用户id分页查询用户公布的故事
     * @param uid
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @GetMapping("myPublik")
    public Result myPublik(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("publik", true);
        IPage<Story> iPage = new Page<>(page, rows);
        List<Story> storyList = storyService.page(iPage, queryWrapper).getRecords();

        if (storyList == null || storyList.size() == 0) return Result.error(ResultTypeEnum.NULL_RESULT);
        else return Result.success(storyList);
    }

    /**
     * 按分类；点赞，收藏，时间；查询所有公布的故事
     * @param type
     * @param sort
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @GetMapping("list")
    public Result list(@RequestParam(value = "type", required = false) String type, @RequestParam(value = "sort", defaultValue = "acclaim") String sort, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        QueryWrapper<Story> queryWrapper = new QueryWrapper<>();
        if (type != null) queryWrapper.eq("type", type);
        queryWrapper.eq("publik", true);
        queryWrapper.orderByDesc(sort);
        IPage<Story> iPage = new Page<>(page, rows);
        List<Story> storyList = storyService.page(iPage, queryWrapper).getRecords();

        if (storyList == null || storyList.size() == 0) return Result.error(ResultTypeEnum.NULL_RESULT);
        else return Result.success(storyList);
    }

    @ResponseBody
    @GetMapping("delete")
    public Result delete(@RequestParam(value = "id") Integer id) {
        boolean isSuccess = storyService.removeById(id);
        if (isSuccess) return Result.success();
        else return Result.error();
    }
}
