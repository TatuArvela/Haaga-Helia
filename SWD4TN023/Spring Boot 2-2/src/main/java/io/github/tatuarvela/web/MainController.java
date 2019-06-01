package io.github.tatuarvela.web;

import io.github.tatuarvela.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class MainController {
    @RequestMapping("/hello")
    public String hello(Model model) {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Kate", "Cole", "kate.cole@example.com"));
        students.add(new Student("Dan", "Brown", "dan.brown@example.com"));
        students.add(new Student("Mike", "Mars", "mike.mars@example.com"));

        model.addAttribute("students", students);
        return "hello";
    }
}
