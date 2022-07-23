package com.guli.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.guli.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程收藏
 * </p>
 *
 * @author Gaoxuxu
 * @since 2022-05-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_course_collect")
@ApiModel(value="CourseCollect对象", description="课程收藏")
public class CourseCollect extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程讲师ID")
    private String courseId;

    @ApiModelProperty(value = "课程专业ID")
    private String memberId;


}
