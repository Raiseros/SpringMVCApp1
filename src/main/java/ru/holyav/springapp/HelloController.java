package ru.holyav.springapp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }



    @RequestMapping(value = "/submitName.html", method = RequestMethod.POST)
    public ModelAndView checkUser(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submit");
        modelAndView.addObject("firstName", name);
        return modelAndView;
    }



}