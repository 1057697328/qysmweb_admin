package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.MessageBack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController
{
    @RequestMapping("/")
    public String Main()
    {
        return "redirect:index.html";
    }

    @RequestMapping("index.html")
    public ModelAndView IndexPage(HttpSession session)
    {
        ModelAndView mav = new ModelAndView();
        if(session.getAttribute("ADMIN")==null)
        {
            mav.addAllObjects(MessageBack.MSG(500,"请您重新登录。"));
            mav.setViewName("login.html");
        }
        else
        {
            mav.setViewName("index.html");
        }
        return mav;
    }



    @RequestMapping("login.html")
    public String LoginPage()
    {
        return "login.html";
    }

}
