package cn.edu.hust.nlp.service.impl;

import cn.edu.hust.nlp.entity.Story;
import cn.edu.hust.nlp.mapper.StoryMapper;
import cn.edu.hust.nlp.service.StoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class StoryServiceImpl extends ServiceImpl<StoryMapper, Story> implements StoryService {
}
