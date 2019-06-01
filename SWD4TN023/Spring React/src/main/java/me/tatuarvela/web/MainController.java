package me.tatuarvela.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // Login
    @RequestMapping(value="/login")
    public String login() {
        return "/loginpage.html";
    }


    // StudentList
    @RequestMapping(value="/")
    public String index() {
        return "/studentlist.html";
    }


}