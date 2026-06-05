package com.example.myboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// / 로 접속하면 /posts로 자동 이동
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {return "redirect:/posts";}
}
