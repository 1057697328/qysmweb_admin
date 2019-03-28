package net.lightwing.mediweb_admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class answerController {

    @RequestMapping("answer_manage.html")
    public String index(){
        return "answer_manage.html";
    }
}
