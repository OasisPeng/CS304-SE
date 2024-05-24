package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.AiContent;
import com.example.cs304.service.AiContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/ai")
public class AiController {
    static String aiPath = "./src/main/java/com/example/cs304/common/ai.py";
    @Autowired
    AiContentService aiContentService;
    @GetMapping("/chat")
    public Result chat(@RequestBody AiContent aiContent) {
//        id = 1 （如果是新对话，直接将id置为-1，发送请求之后重新根据userid查询一遍，将id最大的置为主对话（等于将页面刷新一下将id跟新为数据库赋给的id，便于后面继续对话））
//        user_id = 1
//        content = 你好

        try {
            String res = CommandRun(aiContent);
            return Result.suc(res);
        } catch (Exception e) {
            return Result.fail();
        }
    }
    @GetMapping("/id/{id}")
    public Result GetById(@PathVariable int id) {
        return Result.suc(aiContentService.SelectById(id));
    }
    @DeleteMapping("/id/{id}")
    public Result DeleteById(@PathVariable int id) {
        return Result.suc(aiContentService.DeleteById(id));
    }
    @GetMapping("/user/{user}")
    public Result GetByUser(@PathVariable int user) {
        return Result.suc(aiContentService.SelectByUserName(user));
    }
    private static String CommandRun(AiContent aiContent) {
        try {
            // 设置Python解释器的绝对路径

            // 构建处理器
            ProcessBuilder pb = new ProcessBuilder("python", aiPath, "--content", aiContent.getContent(), "--user", Integer.toString(aiContent.getUser()), "--id", Integer.toString(aiContent.getId()));
            pb.redirectErrorStream(true);
            // 启动Python进程
            Process process = pb.start();

            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                stringBuilder.append(line);
            }

            // 等待Python脚本执行完成
            process.waitFor();

            // 获取Python脚本的退出值
            int exitCode = process.exitValue();
            System.out.println("Python script exited with code: " + exitCode);
            return stringBuilder.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }
}
