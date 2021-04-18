package edu.bjtu.ebookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class showController {
    @RequestMapping("/show")
    public String show(Model model){
        model.addAttribute("msg","hello,springboot");
        return "show";
    }
    public String demo(){
        return "hello,SpringBoot";
    }
}
