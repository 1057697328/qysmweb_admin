package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.common.UPLOAD;
import net.lightwing.mediweb_admin.pojo.MCoop;
import net.lightwing.mediweb_admin.service.MCoopService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class CooperationController {

    @Autowired
    private MCoopService service;

    @RequestMapping("cooperation.html")
    public String index(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {

        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MCoop> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.count(), list);
                model.addAttribute("pageData", bean);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "合作机构信息获取失败"));
            }

            return "cooperation.html";
        }
    }

    @RequestMapping("coop/get/{coid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer coid) {
        try {
            MCoop mCoop = service.selectByPrimaryKey(coid);
            return MessageBack.DATA(200, "获取合作机构信息成功", mCoop);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取合作机构信息失败");
        }
    }

    @RequestMapping("addCoop")
    public String addCoop(@ModelAttribute MCoop coop, Model model, @RequestParam("picture") MultipartFile file) {
        try {
            if (service.selectByName(coop.getConame()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    coop.setImgpath(null);
                    if (coop.getImgpath() != null) {
                        String deletepath = coop.getImgpath().substring(1, coop.getImgpath().length());
                        UPLOAD.deleteFile(deletepath);
                    }
                    Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                    if ((int) upload.get("code") == 200) {
                        coop.setImgpath("/pictures/" + upload.get("filename"));
                    }
                }
                service.insertSelective(coop);
                model.addAttribute("message", MessageBack.MSG(200, "添加合作机构成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加合作机构失败。"));
        }
        return "cooperation.html";
    }

    @RequestMapping("updateCoop")
    public String updateCoop(@ModelAttribute MCoop coop, Model model, @RequestParam("picture") MultipartFile file) {
        try {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                coop.setImgpath(null);
                if (coop.getImgpath() != null) {

                    String deletepath = coop.getImgpath().substring(1, coop.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    coop.setImgpath("/pictures/" + upload.get("filename"));
                }
            }
            service.updateByPrimaryKeySelective(coop);

            model.addAttribute("message", MessageBack.MSG(200, "合作机构更新成功!"));
            return "cooperation.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "合作机构更新失败!"));
            return "cooperation.html";
        }
    }

    @RequestMapping("deleteCoop")
    public String deleteCoop(Integer coid, Model model) {
        try {
            service.deleteByPrimaryKey(coid);
            model.addAttribute("message", MessageBack.MSG(200, "合作机构删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "合作机构删除失败!"));
        }
        return "cooperation.html";
    }
}

