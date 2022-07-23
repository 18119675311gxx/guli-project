package com.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.base.result.R;
import com.guli.service.edu.entity.Teacher;
import com.guli.service.edu.entity.vo.TeacherQueryVo;
import com.guli.service.edu.feign.OssFileService;
import com.guli.service.edu.mapper.TeacherMapper;
import com.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Gaoxuxu
 * @since 2022-05-26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private OssFileService ossFileService;
    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {

        //显示分页查询列表
//        1、排序：按照sort字段排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

//        2、分页查询
        if(teacherQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

//        3、条件查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.likeRight("name", name);
        }

        if(level != null){
            queryWrapper.eq("level", level);
        }

        if(!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("join_date", joinDateBegin);
        }

        if(!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("join_date", joinDateEnd);
        }

        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameList(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name",key);
        List<Map<String, Object>> list = baseMapper.selectMaps(queryWrapper);
        return list;
    }

    @Override
    public boolean removeAvatarById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        if (null != teacher) {
            String avatar = teacher.getAvatar();
            if (!StringUtils.isEmpty(avatar)) {
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }
        return false;
    }
}
