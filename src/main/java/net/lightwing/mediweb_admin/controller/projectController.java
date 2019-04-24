package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.pojo.MProject;
import net.lightwing.mediweb_admin.service.MProectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class projectController {

    @Autowired
    private MProectService service;

    @RequestMapping("project_manage.html")
    public String index(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {

        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MProject> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.count(), list);
                model.addAttribute("pageData", bean);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "項目信息获取失败"));
            }


            return "project_manage.html";

        }
    }

    @RequestMapping("mProject/get/{pid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer pid) {
        try {
            MProject mmProject = service.selectByPrimaryKey(pid);
            return MessageBack.DATA(200, "获取項目信息成功", mmProject);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取項目信息失败");
        }
    }

    @RequestMapping("addProject")
    public String addProject(@ModelAttribute MProject mProject, Model model) {
        try {
            if (service.selectByName(mProject.getPname()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                service.insertSelective(mProject);
                model.addAttribute("message", MessageBack.MSG(200, "添加项目成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加项目失败。"));
        }
        return "project_manage.html";
    }

    @RequestMapping("updateProject")
    public String updateProject(@ModelAttribute MProject mProject, Model model) {
        try {
            service.updateByPrimaryKeySelective(mProject);

            model.addAttribute("message", MessageBack.MSG(200, "項目更新成功!"));
            return "project_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "項目更新失败!"));
            return "project_manage.html";
        }
    }

    @RequestMapping("deleteProject")
    public String deleteProject(Integer pid, Model model) {
        try {
            service.deleteByPrimaryKey(pid);
            model.addAttribute("message", MessageBack.MSG(200, "項目删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "項目删除失败!"));
        }
        return "project_manage.html";
    }
}
