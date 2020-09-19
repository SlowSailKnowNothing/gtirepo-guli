package com.atguigu.demo.eduservice.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {
    @ApiModelProperty(value = "教师名称，模糊查询")
    private  String name;

    @ApiModelProperty(value = "教师等级，模糊查询")
    private Integer level;


    @ApiModelProperty(value = "开始时间，模糊查询")
    private  String begin;


    @ApiModelProperty(value = "结束时间，模糊查询")
    private  String  end;
}
