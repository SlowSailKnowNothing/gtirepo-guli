package com.atguigu.demo.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.demo.eduservice.entity.EduTeacher;
import com.atguigu.demo.eduservice.entity.vo.TeacherQuery;
import com.atguigu.demo.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    @ApiOperation("列出所有讲师的列表")
    public R list()
    {
        return R.ok().data("item",eduTeacherService.list(null));
    }

    @ApiOperation("通过id删除讲师")
    @DeleteMapping("{id}")//id通过路径值传递
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id)
    {
        boolean flag= eduTeacherService.removeById(id);
        if(flag==true)return R.ok();
        else return R.error();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //调用方法实现分页

        eduTeacherService.page(pageTeacher, null);
        long total=pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);

    }

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public  R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery)
    {
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);

        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();

        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        Integer level = teacherQuery.getLevel();
        String name = teacherQuery.getName();

        if(!StringUtils.isEmpty(name))
        {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(begin))
        {
            wrapper.ge("gmt_create",begin);
        }if(!StringUtils.isEmpty(end))
        {
            wrapper.ge("gmt_modified",end);
        }if(!StringUtils.isEmpty(level))
        {
            wrapper.ge("level",level);
        }

        eduTeacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getSize();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }


    @PostMapping("/addTeacher")
    public  R Save(@ApiParam(name = "teacher",value = "讲师对象",required = true)@RequestBody EduTeacher eduTeacher)
    {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save)return R.ok();
        else return R.error();
    }


    @GetMapping("/getTeacher{id}")
    public R getTeacher(@PathVariable String id)
    {
        EduTeacher eduTeacherServiceById = eduTeacherService.getById(id);
        return  R.ok().data("teacher",eduTeacherServiceById);
    }


    @PostMapping("updateTeacher")
    public  R updateTeacher(@RequestBody EduTeacher eduTeacher)
    {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag==true)return R.ok();
        else return R.error();
    }
}

