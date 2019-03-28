package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.pojo.MInductry;
import net.lightwing.mediweb_admin.pojo.MInductryinfo;
import net.lightwing.mediweb_admin.service.MIductrInfoService;
import net.lightwing.mediweb_admin.service.MInductryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class MInductryInfoController {
    @Autowired
    private MIductrInfoService service;

    @Autowired
    private MInductryService mInductryService;

    @RequestMapping("industry_information_manage.html")
    public String NewsManage(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {

        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MInductryinfo> list = service.getList(pageindex, Constant.PAGESIZE);
                List<MInductry> mInductrys = mInductryService.selectAll();
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.count(), list);
                model.addAttribute("pageData", bean);
                model.addAttribute("mInductrys", mInductrys);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "产业信息信息获取失败"));
            }
            return "industry_information_manage.html";
        }

    }

    @RequestMapping("addMInductryinfo")
    public String addNews(@ModelAttribute MInductryinfo mInductryinfo, Model model) {
        try {
            if (service.selectByName(mInductryinfo.getIiname()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                service.insertSelective(mInductryinfo);
                model.addAttribute("message", MessageBack.MSG(200, "添加产业信息成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加产业信息失败。"));
        }
        return "industry_information_manage.html";
    }

    @RequestMapping("mInductryinfo/get/{iiid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer iiid) {
        try {
            MInductryinfo mInductryinfo = service.selectByPrimaryKey(iiid);
            return MessageBack.DATA(200, "获取产业信息信息成功", mInductryinfo);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取产业信息信息失败");
        }
    }

    @RequestMapping("updateMInductryinfo")
    public String updateNews(@ModelAttribute MInductryinfo mInductryinfo, Model model) {
        try {
            service.updateByPrimaryKeySelective(mInductryinfo);

            model.addAttribute("message", MessageBack.MSG(200, "产业信息更新成功!"));
            return "industry_information_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "产业信息更新失败!"));
            return "industry_information_manage.html";
        }
    }

    @RequestMapping("deleteMInductryinfo")
    public String deleteNews(Integer iiid, Model model) {
        try {
            service.deleteByPrimaryKey(iiid);
            model.addAttribute("message", MessageBack.MSG(200, "产业信息删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "产业信息删除失败!"));
        }
        return "industry_information_manage.html";
    }
}
