package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.common.UPLOAD;
import net.lightwing.mediweb_admin.pojo.MDoctor;
import net.lightwing.mediweb_admin.service.MDoctorService;
import net.lightwing.mediweb_admin.service.MDoctorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class expertsController {

    @Autowired
    private MDoctorService service;

    @RequestMapping("experts_manage.html")
    public String index(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {

        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MDoctor> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.count(), list);
                model.addAttribute("pageData", bean);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "专家信息获取失败"));
            }

            return "experts_manage.html";
        }
    }

    @RequestMapping("Experts/get/{coid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer coid) {
        try {
            MDoctor MDoctor = service.selectByPrimaryKey(coid);
            return MessageBack.DATA(200, "获取专家信息成功", MDoctor);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取专家信息失败");
        }
    }

    @RequestMapping("addExperts")
    public String addExperts(@ModelAttribute MDoctor doctor, Model model, @RequestParam("picture") MultipartFile file) {
        try {
            if (service.selectByName(doctor.getDname()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    doctor.setImgpath(null);
                    if (doctor.getImgpath() != null) {
                        String deletepath = doctor.getImgpath().substring(1, doctor.getImgpath().length());
                        UPLOAD.deleteFile(deletepath);
                    }
                    Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                    if ((int) upload.get("code") == 200) {
                        doctor.setImgpath("/pictures/" + upload.get("filename"));
                    }
                }
                service.insertSelective(doctor);
                model.addAttribute("message", MessageBack.MSG(200, "添加专家成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加专家失败。"));
        }
        return "experts_manage.html";
    }

    @RequestMapping("updateExperts")
    public String updateExperts(@ModelAttribute MDoctor doctor, Model model, @RequestParam("picture") MultipartFile file) {
        try {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                doctor.setImgpath(null);
                if (doctor.getImgpath() != null) {

                    String deletepath = doctor.getImgpath().substring(1, doctor.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    doctor.setImgpath("/pictures/" + upload.get("filename"));
                }
            }
            service.updateByPrimaryKeySelective(doctor);

            model.addAttribute("message", MessageBack.MSG(200, "专家更新成功!"));
            return "experts_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "专家更新失败!"));
            return "experts_manage.html";
        }
    }

    @RequestMapping("deleteExperts")
    public String deleteExperts(Integer coid, Model model) {
        try {
            service.deleteByPrimaryKey(coid);
            model.addAttribute("message", MessageBack.MSG(200, "专家删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "专家删除失败!"));
        }
        return "experts_manage.html";
    }

}
