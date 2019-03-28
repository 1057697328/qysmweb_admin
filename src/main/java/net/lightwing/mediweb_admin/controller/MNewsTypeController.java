package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.pojo.MNewstype;
import net.lightwing.mediweb_admin.service.MNewstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class MNewsTypeController {

    @Autowired
    private MNewstypeService service;

    @RequestMapping("newstype_manage.html")
    public String NewstypeManage(HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex, Model model) {
        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MNewstype> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.getCount(), list);
                model.addAttribute("pageData", bean);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "新闻类型信息获取失败"));
            }
            return "newstype_manage.html";
        }
    }


    @RequestMapping("newstype/get/{ntypeid}")
    @ResponseBody
    public Map<String, Object> getNewsType(@PathVariable Integer ntypeid) {
        try {
            MNewstype news = service.getNewsType(ntypeid);
            return MessageBack.DATA(200, "获取新闻类型信息成功", news);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取新闻类型信息失败");
        }
    }


    @RequestMapping("addNewstype")
    public String addNewsType(@ModelAttribute MNewstype newstype, Model model) {
        try {
            if (service.selectByName(newstype.getNtypename()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该名称已存在"));
            } else {
                service.addNewsType(newstype);
                model.addAttribute("message", MessageBack.MSG(200, "新闻类型添加成功!"));
            }
            return "newstype_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "新闻类型添加失败!"));
            return "newstype_manage.html";
        }
    }


    @RequestMapping("updateNewstype")
    public String updateNewstype(@ModelAttribute MNewstype newstype, Model model) {
        try {
            service.updateNewsType(newstype);
            model.addAttribute("message", MessageBack.MSG(200, "新闻类型修改成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "新闻类型修改失败!"));
        }
        return "newstype_manage.html";
    }


    @RequestMapping("deleteNewstype")
    public String deleteNewstype(Integer ntypeid, Model model) {
        try {
            if (service.newsCountByType(ntypeid) > 0) {
                model.addAttribute("message", MessageBack.MSG(200, "删除新闻类型失败,该新闻类型下还有新闻信息,请删除所有该类型的新闻后再试！"));
            } else {
                service.deleteNewsType(ntypeid);
                model.addAttribute("message", MessageBack.MSG(200, "删除向欧盟文类型成功!"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "删除新闻类型失败!"));
        }
        return "newstype_manage.html";
    }

}
