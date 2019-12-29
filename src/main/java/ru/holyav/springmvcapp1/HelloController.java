package ru.holyav.springmvcapp1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;




@Controller
public class HelloController {

    @RequestMapping("/submit")
    public ModelAndView sayHello(HttpServletRequest request){

        String name = (request.getParameter("myName"));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("display.jsp");
        mv.addObject("result", name);

        return mv;
    }

}