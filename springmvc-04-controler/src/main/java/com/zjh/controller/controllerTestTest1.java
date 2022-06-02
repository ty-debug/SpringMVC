package com.zjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controllerTestTest1 {

    @RequestMapping("/t1")
    public String Test1(Model model) {
        model.addAttribute("msg", "controllerTestTest1");
        return "test";
    }

    @RequestMapping("/t2")
    public String Test2(Model model) {
        model.addAttribute("msg", "Test2");
        return "test";
    }

}
