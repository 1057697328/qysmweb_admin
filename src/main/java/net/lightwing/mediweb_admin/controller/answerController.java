package net.lightwing.mediweb_admin.controller;


import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.common.UPLOAD;
import net.lightwing.mediweb_admin.pojo.MQuestion;
import net.lightwing.mediweb_admin.service.MQuestionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class answerController {

    @Autowired
    private MQuestionService service;

    @RequestMapping("answer_manage.html")
    public String index(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {
        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MQuestion> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.count(), list);
                model.addAttribute("pageData", bean);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "科普答疑信息获取失败"));
            }

            return "answer_manage.html";
        }
    }

    @RequestMapping("mQuestion/get/{qid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer qid) {
        try {
            MQuestion mQuestion = service.selectByPrimaryKey(qid);
            return MessageBack.DATA(200, "获取科普答疑信息成功", mQuestion);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取科普答疑信息失败");
        }
    }

    @RequestMapping("addMQuestion")
    public String addmQuestion(@ModelAttribute MQuestion mQuestion, Model model) {
        try {
            if (service.selectByName(mQuestion.getQname()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                mQuestion.setCreatetime(new Date());
                service.insertSelective(mQuestion);
                model.addAttribute("message", MessageBack.MSG(200, "添加科普答疑成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加科普答疑失败。"));
        }
        return "answer_manage.html";
    }

    @RequestMapping("updateMQuestion")
    public String updatemQuestion(@ModelAttribute MQuestion mQuestion, Model model) {
        try {
            service.updateByPrimaryKeySelective(mQuestion);

            model.addAttribute("message", MessageBack.MSG(200, "科普答疑更新成功!"));
            return "answer_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "科普答疑更新失败!"));
            return "answer_manage.html";
        }
    }

    @RequestMapping("deleteMQuestion")
    public String deletemQuestion(Integer qid, Model model) {
        try {
            service.deleteByPrimaryKey(qid);
            model.addAttribute("message", MessageBack.MSG(200, "科普答疑删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "科普答疑删除失败!"));
        }
        return "answer_manage.html";
    }
}
