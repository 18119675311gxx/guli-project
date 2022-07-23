package com.guli.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.service.edu.entity.form.CourseInfoForm;
import com.guli.service.edu.entity.vo.CourseQueryVo;
import com.guli.service.edu.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Gaoxuxu
 * @since 2022-05-26
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoByIf(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);
}
