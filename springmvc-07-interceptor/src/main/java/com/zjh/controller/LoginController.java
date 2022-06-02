package com.zjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {


    @RequestMapping("/main")
    public String main(HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("userLoginInfo"));
        return "main";
    }

    @RequestMapping("/goLogin")
    public String goLogin(HttpSession session){
        if (session.getAttribute("userLoginInfo") != null) {
            return "redirect:/user/main";
        }

        return "login";
    }

    @RequestMapping("/Login")
    public String login(HttpSession session, String username, String password) {
        session.setAttribute("userLoginInfo", username);
        return "redirect:/user/main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userLoginInfo");
        return "redirect:/user/goLogin";
    }


}
