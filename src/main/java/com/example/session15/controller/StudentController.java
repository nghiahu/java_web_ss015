package com.example.session15.controller;

import com.example.session15.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "Ngô Hữu Nghĩa", 19, "KS23","nghia@gmail.com","HP","0911557450"));
        students.add(new Student(2, "Lê Diên Tiến", 19, "KS23","tienYeuNhu@gmail.com","TN","0985820382"));
        students.add(new Student(3, "Nguyễn Văn Đoan", 20,"KS23","doan124@gmail.com","QN","0974924923"));
    }

    @GetMapping("listStudent")
    public String listStudent( Model model) {
        model.addAttribute("students", students);
        return "listStudent";
    }

}
