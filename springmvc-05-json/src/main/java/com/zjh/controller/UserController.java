package com.zjh.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjh.pojo.User;
import com.zjh.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RestController //或者这里写这个注解 也可以让返回值不走视图解析器
public class UserController {

    @RequestMapping("/json1")
//    @ResponseBody //返回值不被视图解析器解析 而是直接返回字符串
    public String json1() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User(3, "这家伙", "男");

        return objectMapper.writeValueAsString(user);
    }

    @RequestMapping("/json2")
    public String json2() {

        List<User> list = new ArrayList<>();
        User user1 = new User(3, "这家伙1", "男");
        User user2 = new User(3, "这家伙2", "男");
        User user3 = new User(3, "这家伙3", "男");
        User user4 = new User(3, "这家伙4", "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        return JsonUtils.getJson(list);
    }

    @RequestMapping("/json3")
    public String json3() {
        Date date = new Date();

        return JsonUtils.getJson(date, "yyyy-HH-dd HH:mm:ss");
    }

    @RequestMapping("/json4")
    public String json4() {
        List<User> list = new ArrayList<>();
        User user1 = new User(3, "这家伙1", "男");
        User user2 = new User(3, "这家伙2", "男");
        User user3 = new User(3, "这家伙3", "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);

//        System.out.println("*******Java对象 转 JSON字符串*******");
//        String str1 = JSON.toJSONString(list);
//        System.out.println("JSON.toJSONString(list)==>" + str1);
//        String str2 = JSON.toJSONString(user1);
//        System.out.println("JSON.toJSONString(user1)==>" + str2);
//
//        System.out.println("\n****** JSON字符串 转 Java对象*******");
//        User jp_user1 = JSON.parseObject(str2, User.class);
//        System.out.println("JSON.parseObject(str2,User.class)==>" + jp_user1);
//
//        System.out.println("\n****** Java对象 转 JSON对象 ******");
//        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
//        System.out.println("(JSONObject) JSON.toJSON(user2)==>" + jsonObject1.getString("name"));
//
//        System.out.println("\n****** JSON对象 转 Java对象 ******");
//        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
//        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>" + to_java_user);


        return JSON.toJSONString(list);
    }


}
