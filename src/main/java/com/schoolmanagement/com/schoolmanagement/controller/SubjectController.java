package com.schoolmanagement.com.schoolmanagement.controller;


import com.schoolmanagement.com.schoolmanagement.entity.Course;
import com.schoolmanagement.com.schoolmanagement.entity.SchoolSubject;
import com.schoolmanagement.com.schoolmanagement.service.CourseService;
import com.schoolmanagement.com.schoolmanagement.service.SchoolSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    CourseService courseService;

    @Autowired
    SchoolSubjectService schoolSubjectService;

    @GetMapping("")
    public String home(){
        return "subject/home";
    }

    @GetMapping("/create")
    public String create(Model model){
        SchoolSubject schoolSubject = new SchoolSubject();
        List<Course> activeCourses = courseService.findAllActiveCourses();
        model.addAttribute("subject", schoolSubject);
        model.addAttribute("activeCourses",activeCourses);
        return "/subject/subject-form";
    }

    @PostMapping("/save")
    public String saveSubject(@ModelAttribute("subject") SchoolSubject subject) {

        schoolSubjectService.save(subject);
        return "redirect:/subject"; // Asumiendo que tienes un endpoint que lista todas las materias
    }
}
