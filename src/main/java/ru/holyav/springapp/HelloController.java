package ru.holyav.springapp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
public class HelloController {

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        List<String> nameList = Arrays.asList("Andrey", "Ivan", "Alexander");
        model.put("student", new Student());
        model.put("nameList", nameList);
        return "index" ;
    }


    @RequestMapping(value = "submitName.html", method = RequestMethod.POST)
    public String checkUser(@ModelAttribute("student") Student student) {
        return "submit";

    }

    @RequestMapping(value ="registration", method = RequestMethod.GET)
    public String registry() {
        return "registration" ;
    }

    @RequestMapping(value ="save", method = RequestMethod.POST)
    public String addName(@RequestParam("name") String name, Map<String, Object> model) {
        List<String> nameList = Arrays.asList("Andrey", "Ivan", "Alexander", name);
        model.put("student", new Student());
        model.put("nameList", nameList);
        return "index" ;
    }
}

