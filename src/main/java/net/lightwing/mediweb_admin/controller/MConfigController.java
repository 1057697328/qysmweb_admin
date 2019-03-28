package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.pojo.MConfig;
import net.lightwing.mediweb_admin.service.MConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MConfigController
{

    @Autowired
    private MConfigService service;

    @RequestMapping("config_manage.html")
    public String ConfigManage(Model model, HttpSession session)
    {
        if(session.getAttribute("ADMIN")==null)
        {
            model.addAllAttributes(MessageBack.MSG(500,"请您重新登录。"));
            return "login.html";
        }
        else
        {
            try
            {
                MConfig data = service.getConfig(Constant.CONFIGID);
                model.addAttribute("data",data);
            }
            catch (Exception e)
            {
                model.addAttribute("message",MessageBack.MSG(500,"系统基本信息获取失败"));
            }
            return "config_manage.html";
        }
    }

    @RequestMapping("updateConfig")
    public String updateConfig(@ModelAttribute MConfig config,Model model)
    {
        try
        {
            service.updateConfig(config);
            model.addAttribute("message",MessageBack.MSG(200,"系统基本信息更新成功。"));
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"系统基本信息更新失败。"));
        }
        return "config_manage.html";
    }
}
