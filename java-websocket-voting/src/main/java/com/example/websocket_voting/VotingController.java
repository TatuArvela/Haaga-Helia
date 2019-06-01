package com.example.websocket_voting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VotingController {

    @RequestMapping(path="/")
    public String getVoting(Model model) {
        return "voting";
    }

}
