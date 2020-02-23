package cn.edu.hust.nlp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@MapperScan("cn.edu.hust.nlp.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
