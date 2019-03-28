package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.pojo.MInductry;
import net.lightwing.mediweb_admin.service.MInductryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class MIndustryController {

    @Autowired
    private MInductryService service;

    @RequestMapping("industry_plate_manage.html")
    public String index(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {
        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MInductry> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.count(), list);
                model.addAttribute("pageData", bean);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "产业板块信息获取失败"));
            }
            return "industry_plate_manage.html";

        }
    }

    @RequestMapping("industry/get/{iid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer iid) {
        try {
            MInductry mInductry = service.selectByPrimaryKey(iid);
            return MessageBack.DATA(200, "获取产业板块信息成功", mInductry);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取产业板块信息失败");
        }
    }

    @RequestMapping("addIndustry")
    public String addIndustry(@ModelAttribute MInductry mInductry, Model model) {
        try {
            if (service.selectByName(mInductry.getIname()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                service.insertSelective(mInductry);
                model.addAttribute("message", MessageBack.MSG(200, "添加产业板块成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加产业板块失败。"));
        }
        return "industry_plate_manage.html";
    }

    @RequestMapping("updateIndustry")
    public String updateIndustry(@ModelAttribute MInductry mInductry, Model model) {
        try {

            service.updateByPrimaryKeySelective(mInductry);
            model.addAttribute("message", MessageBack.MSG(200, "产业板块更新成功!"));
            return "industry_plate_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "产业板块更新失败!"));
            return "industry_plate_manage.html";
        }
    }

    @RequestMapping("deleteIndustry")
    public String deleteIndustry(Integer iid, Model model) {
        try {
            service.deleteByPrimaryKey(iid);
            model.addAttribute("message", MessageBack.MSG(200, "产业板块删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "产业板块删除失败!"));
        }
        return "industry_plate_manage.html";
    }
}
