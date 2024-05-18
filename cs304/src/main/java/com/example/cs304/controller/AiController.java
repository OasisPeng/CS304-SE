package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.AiContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/ai")
public class AiController {
    static String aiPath = "./src/main/java/com/example/cs304/common/ai.py";

    @GetMapping("/chat")
    public Result chat(@RequestBody AiContent aiContent) {
        try {
            String res = CommandRun(aiContent);
            return Result.suc(res);
        } catch (Exception e) {
            return Result.fail();
        }
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
