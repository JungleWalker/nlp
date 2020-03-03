package cn.edu.hust.nlp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Story {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 作者id
     */
    private Integer uid;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String text;

    /**
     * 故事生成时间
     */
    private Date date;

    /**
     * 故事类型：
     */
    private String type;

    /**
     * 是否公开
     */
    private Boolean publik;

    /**
     * 点赞数
     */
    private Integer acclaim;

    /**
     * 收藏数
     */
    private Integer collections;
}
