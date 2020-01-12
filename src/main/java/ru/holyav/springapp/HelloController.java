package ru.holyav.springapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String main(Model model) {
        List<Student> theStudents = studentService.getStudents();
        model.addAttribute("student", new Student());
        model.addAttribute("students", theStudents);
        return "index";
    }


    @RequestMapping(value = "submitName.html", method = RequestMethod.POST)
    public String checkUser(@ModelAttribute("student") Student student) {
        return "submit";

    }

    @RequestMapping(value ="registration", method = RequestMethod.GET)
    public String registry(Model model) {
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        return "registration";
    }

    @RequestMapping(value ="saveStudent", method = RequestMethod.POST)
    public String addName(@ModelAttribute("student") Student theStudent) {
       studentService.saveStudent(theStudent);

        return "redirect:/";
    }
}

