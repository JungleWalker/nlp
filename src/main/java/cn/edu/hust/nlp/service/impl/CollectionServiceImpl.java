package cn.edu.hust.nlp.service.impl;

import cn.edu.hust.nlp.entity.Collection;
import cn.edu.hust.nlp.mapper.CollectionMapper;
import cn.edu.hust.nlp.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
}
