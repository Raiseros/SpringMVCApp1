package ru.holyav.springmvcapp1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/submit")
    public String sayHello(){
        return ("display.jsp");
    }

}
