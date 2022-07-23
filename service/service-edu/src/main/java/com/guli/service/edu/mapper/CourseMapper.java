package com.guli.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.service.edu.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Gaoxuxu
 * @since 2022-05-26
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selectPageByCourseQueryVo(Page<CourseVo> pageParam, QueryWrapper<CourseVo> queryWrapper);
}
