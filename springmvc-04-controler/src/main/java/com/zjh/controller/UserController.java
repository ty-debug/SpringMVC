package com.zjh.controller;

import com.zjh.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

//    http://localhost:8080/springmvc_04_controler_war_exploded/user/t1?name="myName"

    @RequestMapping("/user/t1")
    public String test1(@RequestParam("username") String name, Model model) {

        System.out.println("前端参数：" + name);

        model.addAttribute("msg", name);

        return "test";
    }

    //    Restful
    @RequestMapping("/user/t2/{name}")
    public String test2(@PathVariable String name, Model model) {

        System.out.println("前端参数：" + name);

        model.addAttribute("msg", name);

        return "test";
    }

    /*
        接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接试用
        假设传递的是一个对象，匹配对象中的字段名，名字一致则ok 不一致则为null
     */

    @GetMapping("/user/t3")
    public String test3(User user) {

        System.out.println("前端参数：" + user);

        return "test";
    }

    @RequestMapping("/user/t4")
    public String test4(String name, Model model) {
        System.out.println(name);

        model.addAttribute("msg", name);

        return "test";
    }

}
