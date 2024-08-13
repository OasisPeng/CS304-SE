package com.example.cs304.common.courseData;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CourseImporter {

    public static void main(String[] args) {
        try {
            List<Map<String, String>> courseInfos = readCourseInfoFromJson("D:\\IdeaProjects\\cs304\\src\\main\\java\\com\\example\\cs304\\common\\courseData\\courseInfo.json");
            if (courseInfos != null) {
                importCourseInfoToDatabase(courseInfos);
                System.out.println("数据导入成功！");
            } else {
                System.out.println("无法读取课程信息！");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, String>> readCourseInfoFromJson(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonFilePath), List.class);
    }

    private static void importCourseInfoToDatabase(List<Map<String, String>> courseInfos) throws SQLException {
        String url = "jdbc:mysql://8.138.27.126:3306/se";
        String username = "seuser";
        String password = "selocalhost";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String insertQuery = "INSERT INTO course (trainingType, courseCode, chineseName, EnglishName, courseNature, courseCategory, language, credits, hours, department, teacher) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            for (Map<String, String> courseInfo : courseInfos) {
                preparedStatement.setString(1, courseInfo.get("trainingType"));
                preparedStatement.setString(2, courseInfo.get("courseCode"));
                preparedStatement.setString(3, courseInfo.get("chineseName"));
                preparedStatement.setString(4, courseInfo.get("EnglishName"));
                preparedStatement.setString(5, courseInfo.get("courseNature"));
                preparedStatement.setString(6, courseInfo.get("courseCategory"));
                preparedStatement.setString(7, courseInfo.get("language"));
                preparedStatement.setDouble(8, Double.parseDouble(courseInfo.get("credits")));
                preparedStatement.setDouble(9, Double.parseDouble(courseInfo.get("hours")));
                preparedStatement.setString(10, courseInfo.get("department"));
                preparedStatement.setString(11, courseInfo.get("teacher"));

                preparedStatement.addBatch(); // 加入批处理
            }

            preparedStatement.executeBatch(); // 执行批量插入

        }
    }
}
