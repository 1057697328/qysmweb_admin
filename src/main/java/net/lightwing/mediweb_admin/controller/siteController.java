package net.lightwing.mediweb_admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class siteController {

    @RequestMapping("site_manage.html")
    public String index(){
        return "site_manage.html";
    }
}
