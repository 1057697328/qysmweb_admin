package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.pojo.MAdmin;
import net.lightwing.mediweb_admin.service.MAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class MAdminController
{
    @Autowired
    private MAdminService service;

    @RequestMapping("/login")
    public String Login(@ModelAttribute MAdmin admin, HttpSession session, Model model)
    {

        if(service.login(admin))
        {
            MAdmin data = service.getAdminByAccount(admin.getAccount());
            session.setAttribute("ADMIN",data);
            model.addAttribute("ADMIN",data);
            return "redirect:../index.html";
        }
        else
        {
            model.addAllAttributes(MessageBack.MSG(500,"您的用户名或密码有误，请确认无误后再试。"));
            return "login.html";
        }
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Model model)
    {
        model.addAttribute("message","您可以联系长沙轻翼网络科技有限公司为您重置密码，联系电话为:0731-89793867");
        return "forgot-password.html";
    }
}
