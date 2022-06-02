package com.zjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestfulController {

//    普通方式    ：http://localhost:8080/springmvc_04_controler_war_exploded/add?a=1&b=2
//    Restful   ：http://localhost:8080/springmvc_04_controler_war_exploded/add/a/b

//    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
    @GetMapping("/add/{a}/{b}")
    public String test(@PathVariable int a, @PathVariable int b, Model model) {

        int res = a + b;

        model.addAttribute("msg", res);
        return "test";
    }

}
