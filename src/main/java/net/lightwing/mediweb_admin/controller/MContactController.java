package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.pojo.MContact;
import net.lightwing.mediweb_admin.pojo.MNewstype;
import net.lightwing.mediweb_admin.service.MContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@SuppressWarnings("ALL")
@Controller
public class MContactController
{
    @Autowired
    private MContactService service;

    @RequestMapping("contact_manage.html")
    public String ContactManage(Model model, HttpSession session,@RequestParam(name = "pageindex",required = false) Integer pageindex)
    {
        if(session.getAttribute("ADMIN")==null)
        {
            model.addAllAttributes(MessageBack.MSG(500,"请您重新登录。"));
            return "login.html";
        }
        else
        {
            if(pageindex==null)
            {
                pageindex=1;
            }
            try {
                List<MContact> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.getCount(), list);
                model.addAttribute("pageData", bean);
            }
            catch (Exception e)
            {
                model.addAttribute("message",MessageBack.MSG(500,"留言信息获取失败"));
            }
            return "contact_manage.html";
        }
    }
}
