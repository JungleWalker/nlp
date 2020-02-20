package cn.edu.hust.nlp.service.impl;

import cn.edu.hust.nlp.entity.User;
import cn.edu.hust.nlp.mapper.UserMapper;
import cn.edu.hust.nlp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
