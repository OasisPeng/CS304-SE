package com.example.cs304.entity;

import lombok.Data;

@Data
public class CourseForTimetable {
    String teacher; //老师名字
    String englishName; //中文名
    String chineseName; //英文名字
    String teachingBuilding; //位置
    Integer xq; //星期几
    Integer jc; //节次
    Integer color; //颜色编号（同一个课的lab和大课一样）
    String startTime; //开始时间
    String endTime; //下课时间
}
