package com.example.cs304.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.cs304.entity.Course;
import com.example.cs304.entity.CourseForTimetable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;


public class Util {
    private static HashMap<Integer, String> timeConvert = new HashMap<>() {
        {
            put(1, "8:00");
            put(2, "9:50");
            put(3, "10:20");
            put(4, "12:10");
            put(5, "14:00");
            put(6, "15:50");
            put(7, "16:20");
            put(8, "18:10");
            put(9, "19:00");
            put(10, "20:50");
            put(11, "21:00");
            put(12, "21:50");
        }
    };


    public static String[] TimeConvert(String time, int index) {
        String[] raw = time.split("]");
        String t = raw[index];
        Integer st = Integer.valueOf(t.substring(1,2));
        Integer et;
        if (t.charAt(4) == '节') {
            et = Integer.valueOf(t.substring(3,4));
        } else {
            et = Integer.valueOf(t.substring(3,5));
        }
        String[] times = new String[2];
        times[0] = timeConvert.get(st);
        times[1] = timeConvert.get(et);
        return times;
    }

    public static ArrayList<CourseForTimetable> getCourses(String coursesJSON) {
        if (Objects.equals(coursesJSON, "")) {
            return null;
        }
        System.out.println(coursesJSON);
        HashSet<String> name = new HashSet<>();
        JSONArray courses = JSON.parseArray(coursesJSON);
        ArrayList<CourseForTimetable> courseForTimetables = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            CourseForTimetable courseForTimetable = new CourseForTimetable();
            JSONObject course = courses.getJSONObject(i);
            String rwh = course.getString("RWH");
            if (name.contains(rwh)) {
                continue;
            } else {
                name.add(rwh);
            }
            String SKSJ = course.getString("SKSJ");
            String[] details = SKSJ.split("\n");
            courseForTimetable.setTeacher(details[1]);
            String[] keBanYu = details[2].split("-");
            courseForTimetable.setChineseName(keBanYu[0] + "]");
            if (details[0].contains("体育VI")) {
                courseForTimetable.setClasses("[" + keBanYu[2]);
                courseForTimetable.setLanguage("[" + keBanYu[1] + "]");
            } else {
            courseForTimetable.setClasses("[" + keBanYu[1] + "]");
            courseForTimetable.setLanguage("[" + keBanYu[2]);
            }
            String[] times;
            if (details.length > 4) {
                courseForTimetable.setWeeks(details[3]);
                String[] buildingDetail = details[4].split("]");
                courseForTimetable.setTeachingBuilding(buildingDetail[0] + "]");
                courseForTimetable.setJc(buildingDetail[1] + "]");
                times = TimeConvert(details[4], 1);
            }
            else {
                String[] buildingDetail = details[3].split("]");
                courseForTimetable.setWeeks(buildingDetail[0] + "]");
                courseForTimetable.setTeachingBuilding(buildingDetail[1] + "]");
                courseForTimetable.setJc(buildingDetail[2] + "]");
                times = TimeConvert(details[3], 2);
            }

            courseForTimetable.setEnglishName(course.getString("SKSJ_EN").split("\n")[2]);
            String[] XQJZ = course.getString("KEY").split("_");
            courseForTimetable.setXq(Integer.parseInt(XQJZ[0].split("xq")[1]));
            courseForTimetable.setColor(Integer.parseInt(course.getString("XB")));
            courseForTimetable.setStartTime(times[0]);
            courseForTimetable.setEndTime(times[1]);
            courseForTimetables.add(courseForTimetable);
        }
        return courseForTimetables;
    }
    public static String getCourInfo(String route, String js) throws IOException {
        if (Objects.equals(route, "") || (Objects.equals(js, ""))) {
            return "";
        }
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRedirectStrategy(new DisableRedirectStrategy())
                .build();
        Header[] headers = new Header[3];
        Header header = new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.0.0 Safari/537.36");
        Header header1 = new BasicHeader("x-requested-with", "XMLHttpRequest");
        Header header2 = new BasicHeader("cookie", "route=" + route + "; JSESSIONID=" + js +";");
        headers[0] = header;
        headers[1] = header1;
        headers[2] = header2;
        String selectString = "https://tis.sustech.edu.cn/xszykb/queryxszykbzong";
        String[] XNXQ = checkXNXQ(route, js);
        List<NameValuePair> params = new ArrayList<>();
//             /*添加post请求参数*/
        params.add(new BasicNameValuePair("xn", XNXQ[0]));
        params.add(new BasicNameValuePair("xq", XNXQ[1]));
        HttpPost postLoginRequest = new HttpPost(selectString);
        postLoginRequest.setHeaders(headers);
        postLoginRequest.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(postLoginRequest);
        String res = getResponseString(response);
        release(response, httpClient);
        return res;
    }
    public static String[] casLogin(String username, String password) throws IOException, URISyntaxException {
        System.out.println("[\u001B[0;36m!\u001B[0m] " + "测试CAS链接...");
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRedirectStrategy(new DisableRedirectStrategy())
                .build();
        Header[] headers = new Header[2];
        Header header = new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.0.0 Safari/537.36");
        Header header1 = new BasicHeader("x-requested-with", "XMLHttpRequest");
        headers[0] = header;
        headers[1] = header1;
        try {
            // CAS登录URL
            String loginUrl = "https://cas.sustech.edu.cn/cas/login?service=https%3A%2F%2Ftis.sustech.edu.cn%2Fcas";
            // 发送GET请求获取CAS登录页面
            HttpGet getLoginRequest = new HttpGet(loginUrl);
            getLoginRequest.setHeaders(headers);
            CloseableHttpResponse loginResponse = httpClient.execute(getLoginRequest);
            // 检查响应状态码
            if (loginResponse.getStatusLine().getStatusCode() == 200) {
                System.out.println("[\u001B[0;32m+\u001B[0m] " + "成功连接到CAS...");
            } else {
                System.out.println("[\u001B[0;31mx\u001B[0m] " + "不能访问CAS, 请检查您的网络连接状态");
                return new String[]{"", ""};
            }
            // 解析HTML获取execution
            String execution = parseExecution(loginResponse);
            List<NameValuePair> params = new ArrayList<>();
//             /*添加post请求参数*/
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("execution", execution));
            params.add(new BasicNameValuePair("_eventId", "submit"));
            // 发送POST请求进行登录
            HttpPost postLoginRequest = new HttpPost(loginUrl);

            postLoginRequest.setHeaders(headers);

            postLoginRequest.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse postLoginResponse = httpClient.execute(postLoginRequest);
            Header[] headers2 = postLoginResponse.getAllHeaders();
            // 检查是否登录成功
            if (postLoginResponse.containsHeader("Location")) {
                System.out.println("[\u001B[0;32m+\u001B[0m] " + "登录成功");
            } else {
                System.out.println("[\u001B[0;31mx\u001B[0m] " + "用户名或密码错误，请检查");
                return new String[]{"", ""};
            }

            // 获取重定向后的页面，从中提取cookie信息
            String redirectedUrl = postLoginResponse.getFirstHeader("Location").getValue();
            HttpGet getRedirectedRequest = new HttpGet(redirectedUrl);
            CloseableHttpResponse redirectedResponse = httpClient.execute(getRedirectedRequest);
            String route = extractCookieValue(redirectedResponse, "route");
            String jsessionId = extractCookieValue(redirectedResponse, "JSESSIONID");
            release(redirectedResponse, httpClient);
            loginResponse.close();
            return new String[]{route, jsessionId};

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String parseExecution(CloseableHttpResponse response) throws IOException {
        String responseBody = getResponseString(response);
        return responseBody.split("name=\"execution\" value=\"")[1].split("\"")[0];
    }
    private static String getResponseString(CloseableHttpResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder responseBody = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        return responseBody.toString();
    }
    private static String[] checkXNXQ(String route, String js) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRedirectStrategy(new DisableRedirectStrategy())
                .build();
        Header[] headers = new Header[3];
        Header header = new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.0.0 Safari/537.36");
        Header header1 = new BasicHeader("x-requested-with", "XMLHttpRequest");
        Header header2 = new BasicHeader("cookie", "route=" + route + "; JSESSIONID=" + js +";");
        headers[0] = header;
        headers[1] = header1;
        headers[2] = header2;
        String checkURL = "https://tis.sustech.edu.cn/component/querydangqianxnxq";
        HttpPost postLoginRequest = new HttpPost(checkURL);
        postLoginRequest.setHeaders(headers);
        CloseableHttpResponse httpResponse = httpClient.execute(postLoginRequest);
        String re = getResponseString(httpResponse);
        JSONObject jsonObject = JSON.parseObject(re);
        String[] result = new String[2];
        result[0] = (String) jsonObject.get("XN");
        result[1] = (String) jsonObject.get("XQ");
        release(httpResponse, httpClient);
        return result;
    }
    private static String extractCookieValue(CloseableHttpResponse response, String cookieName) throws IOException {
        System.out.println(getResponseString(response));
        Header[] cookieHeader = response.getHeaders("Set-Cookie");
        int index = 0;
        for (int i = 0; i < cookieHeader.length; i++) {
            if (cookieHeader[i].getValue().contains(cookieName)) {
                index = i;
            }
        }
        return cookieHeader[index].getValue().split(cookieName + "=")[1].split(";")[0];
    }
    private static class DisableRedirectStrategy extends LaxRedirectStrategy {
        @Override
        protected boolean isRedirectable(String method) {
            // 禁止所有的重定向
            return false;
        }
    }
    public static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
        // 释放资源
        if (httpResponse != null) {
            httpResponse.close();
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }
    public static int[] calculateWeek(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekOfYear = date.get(weekFields.weekOfWeekBasedYear()); //2024年1月1日是第一周周一
        int dayOfWeek = date.getDayOfWeek().getValue();
        return new int[]{weekOfYear, dayOfWeek};
    }
    public static int[] getCalculateDate(String date) {
        if (StringUtils.isBlank(date) || date.equals("null")) { //查询当前日期
            date = LocalDate.now().toString();
        }
        LocalDate time = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return calculateWeek(time);
    }
    public static boolean loginVerify(String username, String password) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRedirectStrategy(new DisableRedirectStrategy())
                .build();
        Header[] headers = new Header[2];
        Header header = new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.0.0 Safari/537.36");
        Header header1 = new BasicHeader("x-requested-with", "XMLHttpRequest");
        headers[0] = header;
        headers[1] = header1;
        try {
            // CAS登录，使用CAS的身份认证服务验证学号密码
            String loginUrl = "https://cas.sustech.edu.cn/cas/login";
            // 发送GET请求获取CAS登录页面
            HttpGet getLoginRequest = new HttpGet(loginUrl);
            getLoginRequest.setHeaders(headers);
            CloseableHttpResponse loginResponse = httpClient.execute(getLoginRequest);
            // 检查响应状态码
            if (loginResponse.getStatusLine().getStatusCode() == 200) {
                System.out.println("[\u001B[0;32m+\u001B[0m] " + "成功连接到CAS...");
            } else {
                System.out.println("[\u001B[0;31mx\u001B[0m] " + "不能访问CAS, 请检查您的网络连接状态");
            }
            // 解析HTML获取execution
            String execution = parseExecution(loginResponse);
            List<NameValuePair> params = new ArrayList<>();
            /*添加post请求参数*/
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("execution", execution));
            params.add(new BasicNameValuePair("_eventId", "submit"));
            // 发送POST请求进行登录
            HttpPost postLoginRequest = new HttpPost(loginUrl);
            postLoginRequest.setHeaders(headers);
            postLoginRequest.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse postLoginResponse = httpClient.execute(postLoginRequest);
            System.out.println(postLoginResponse);
            // 检查是否登录成功
            if (postLoginResponse.getStatusLine().getStatusCode() == 200 && postLoginResponse.containsHeader("Set-Cookie")) {
                HttpEntity entity = postLoginResponse.getEntity();
//                System.out.println("Payload:");
//                System.out.println(EntityUtils.toString(entity));
                return EntityUtils.toString(entity).contains("登录成功 -  Central Authentication Service");
            }
            return false;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
