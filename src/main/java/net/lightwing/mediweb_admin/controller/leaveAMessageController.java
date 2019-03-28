package net.lightwing.mediweb_admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class leaveAMessageController {


    @RequestMapping("leaveAMessage_manage.html")
    public String index(){
        return "leaveAMessage_manage.html";
    }
}
