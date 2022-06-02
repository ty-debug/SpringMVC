package com.zjh.controller;

import com.zjh.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @RequestMapping("/t1")
    public String test() {
        return "Hello World";
    }

    @RequestMapping("/a1")
    public void a1(String name, HttpServletResponse response) {
        System.out.println("name = " + name);
        if ("zjh".equals(name)) {
            try {
                response.getWriter().write("success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.getWriter().write("fail");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/a2")
    public List<User> a2() {
        List<User> list = new ArrayList<>();
        list.add(new User("zjh", 1, "男"));
        list.add(new User("zjh2", 11, "男"));
        list.add(new User("zjh2", 111, "男"));

        return list;
    }

    @RequestMapping("/a3")
    public String a3(String name, String pwd) {
        String msg = "";
        if (name!=null){
            if ("admin".equals(name)) {
                msg = "name行";
            } else {
                msg = "name不行";
            }
        }

        if (pwd!=null){
            if ("123456".equals(pwd)) {
                msg = "pwd行";
            } else {
                msg = "pwd不行";
            }
        }


        return msg;
    }

}
