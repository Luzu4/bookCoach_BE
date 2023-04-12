package com.bookcoach.book_coach_be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @RequestMapping("/hello")
    public String hello(){
        return "/hello";
    }

}
