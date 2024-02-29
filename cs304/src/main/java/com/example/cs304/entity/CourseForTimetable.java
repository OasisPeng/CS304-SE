package com.example.cs304.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "CourseForTimetable")
@Data
public class CourseForTimetable {
    String teacher; //老师名字
    String englishName; //中文名
    String chineseName; //英文名字
    String teachingBuilding; //位置
    Integer xq; //星期几
    String weeks; //多少周
    String jc; //节次
    Integer color; //颜色编号（同一个课的lab和大课一样）
    String startTime; //开始时间
    String endTime; //下课时间
}
