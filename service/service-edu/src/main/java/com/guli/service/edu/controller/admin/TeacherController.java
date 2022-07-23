package com.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.base.result.R;
import com.guli.service.edu.entity.Course;
import com.guli.service.edu.entity.Teacher;
import com.guli.service.edu.entity.vo.TeacherQueryVo;
import com.guli.service.edu.feign.OssFileService;
import com.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Gaoxuxu
 * @since 2022-05-26
 */
@Slf4j
@Api(description = "讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private OssFileService ossFileService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/list")
    public R listAll () {
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据ID删除讲师", notes = "根据ID删除讲师，逻辑删除")
    @DeleteMapping("/remove/{id}")
    public R removeById (@PathVariable String id) {
        teacherService.removeAvatarById(id);
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("讲师列表查询对象") TeacherQueryVo teacherQueryVo){

        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师对象") @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher != null){
            return R.ok().data("item", teacher);
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation(value = "根据ID列表删除讲师")
    @DeleteMapping("batch-remove")
    public R removeRows(
            @ApiParam(value = "讲师ID列表", required = true)
            @RequestBody List<String> idList){

        boolean result = teacherService.removeByIds(idList);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "关键字", required = true)
            @PathVariable String key){
        List<Map<String, Object>> nameList = teacherService.selectNameList(key);

        return R.ok().data("nameList", nameList);
    }

    @ApiOperation("测试feignClient")
    @GetMapping("test/feign")
    public R testFeign(){
        log.info("edu调用成功");
        ossFileService.test();
        return R.ok();
    }

    public static void main(String[] args) {
        Course course = new Course();
        course.setCover("1");
        Course course1 = new Course();
        course1.setCover("2");
        Course course2 = new Course();
        course1.setCover("3");
        Course course3 = new Course();
        course1.setCover("2");
        ArrayList<Course> objects = new ArrayList<>();
        objects.add(course);
        objects.add(course1);
        objects.add(course2);
        objects.add(course3);
        System.out.println();

    }
}

