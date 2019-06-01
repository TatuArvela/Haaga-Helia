package io.github.tatuarvela.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name, @RequestParam("age") Integer age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello";
    }
}
