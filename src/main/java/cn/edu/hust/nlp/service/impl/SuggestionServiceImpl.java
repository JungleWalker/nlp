package cn.edu.hust.nlp.service.impl;

import cn.edu.hust.nlp.entity.Suggestion;
import cn.edu.hust.nlp.mapper.SuggestionMapper;
import cn.edu.hust.nlp.service.SuggestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SuggestionServiceImpl extends ServiceImpl<SuggestionMapper, Suggestion> implements SuggestionService {
}
