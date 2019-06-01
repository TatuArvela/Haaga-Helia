package io.github.tatuarvela.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {
    ArrayList<String> friends = new ArrayList<>();

    @RequestMapping("/index")
    public String hello(@RequestParam(value = "friend", required=false) String friend, Model model) {
        if (friend != null) {
            friends.add(friend);
        }

        model.addAttribute("friends", friends);
        return "index";
    }
}
