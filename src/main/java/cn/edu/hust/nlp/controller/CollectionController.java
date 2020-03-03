package cn.edu.hust.nlp.controller;

import cn.edu.hust.nlp.entity.Collection;
import cn.edu.hust.nlp.entity.Story;
import cn.edu.hust.nlp.service.CollectionService;
import cn.edu.hust.nlp.service.StoryService;
import cn.edu.hust.nlp.vo.Result;
import cn.edu.hust.nlp.vo.ResultTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    StoryService storyService;

    @RequestMapping("collect")
    public Result collect(@RequestParam(value = "uid") Integer uid, @RequestParam(value = "sid") Integer sid) {
        Collection collection = new Collection();
        collection.setUid(uid);
        collection.setSid(sid);

        boolean isSuccess = collectionService.save(collection);
        if (isSuccess) {
            Story story = storyService.getById(sid);
            story.setCollections(story.getCollections() + 1);
            storyService.updateById(story);
            return Result.success();
        } else return Result.error();
    }

    @ResponseBody
    @RequestMapping("delete")
    public Result delete(@RequestParam(value = "id") Integer id) {
        Collection collection = collectionService.getById(id);
        boolean isSuccess = collectionService.removeById(id);
        if (isSuccess) {
            Story story = storyService.getById(collection.getSid());
            story.setCollections(story.getCollections() - 1);
            storyService.updateById(story);
            return Result.success();
        } else return Result.error();
    }

    @ResponseBody
    @RequestMapping("myList")
    public Result myList(@RequestParam(value = "uid") Integer uid) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        List<Collection> collectionList = collectionService.list(queryWrapper);
        if (collectionList == null || collectionList.size() == 0) return Result.error(ResultTypeEnum.NULL_RESULT);
        else return Result.success(collectionList);
    }
}
