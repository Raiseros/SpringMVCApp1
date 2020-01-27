package ru.holyav.springapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.holyav.springapp.entity.Student;
import ru.holyav.springapp.service.StudentService;

import java.util.List;



@Controller
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String getStudents(Model model) {
     List<Student> theStudents = studentService.getStudents();
     model.addAttribute("students", theStudents);
        return "list-students";
    }


    @RequestMapping(value ="registration", method = RequestMethod.GET)
    public String registry(Model model) {
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        return "registration";
    }

    @RequestMapping(value ="formForUpdate", method = RequestMethod.GET)
    public String update(@RequestParam("studentId") int theId, Model model) {
        Student theStudent = studentService.getStudent(theId);
        model.addAttribute("student", theStudent);
        return "registration";
    }


    @RequestMapping(value ="saveStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student theStudent) {
       studentService.saveStudent(theStudent);

        return "redirect:/";
    }


    @RequestMapping(value ="delete", method = RequestMethod.GET)
    public String delete(@RequestParam("studentId") int theId) {
        studentService.deleteStudent(theId);
        return "redirect:/";
    }
}

