package cn.edu.hust.nlp.controller;

import cn.edu.hust.nlp.entity.Suggestion;
import cn.edu.hust.nlp.service.SuggestionService;
import cn.edu.hust.nlp.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;

    @ResponseBody
    @RequestMapping("suggest")
    public Result suggest(@RequestParam(value = "text") String text) {
        Suggestion suggestion = new Suggestion();
        suggestion.setText(text);
        suggestion.setDate(new Date());

        boolean isSuccess = suggestionService.save(suggestion);
        if (isSuccess) return Result.success();
        else return Result.error();
    }
}
