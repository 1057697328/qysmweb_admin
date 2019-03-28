package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.common.UPLOAD;
import net.lightwing.mediweb_admin.pojo.MNews;
import net.lightwing.mediweb_admin.pojo.MNewstype;
import net.lightwing.mediweb_admin.service.MNewsService;
import net.lightwing.mediweb_admin.service.MNewstypeService;
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

@SuppressWarnings("ALL")
@Controller
public class MNewsController {
    @Autowired
    private MNewsService service;

    @Autowired
    private MNewstypeService newstypeService;

    @RequestMapping("news_manage.html")
    public String NewsManage(Model model, HttpSession session, @RequestParam(name = "pageindex", required = false) Integer pageindex) {

        if (session.getAttribute("ADMIN") == null) {
            model.addAllAttributes(MessageBack.MSG(500, "请您重新登录。"));
            return "login.html";
        } else {
            if (pageindex == null) {
                pageindex = 1;
            }
            try {
                List<MNews> list = service.getList(pageindex, Constant.PAGESIZE);
                List<MNewstype> newstypes = newstypeService.getList();
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.getCount(), list);
                model.addAttribute("pageData", bean);
                model.addAttribute("newstypes", newstypes);
            } catch (Exception e) {
                model.addAttribute("message", MessageBack.MSG(500, "新闻信息获取失败"));
            }
            return "news_manage.html";
        }

    }

    @RequestMapping("addNews")
    public String addNews(@ModelAttribute MNews news, Model model, @RequestParam("picture") MultipartFile file) {
        try {
            if (service.selectByName(news.getNtitle()) != null) {
                model.addAttribute("message", MessageBack.MSG(200, "该标题已存在。"));
            } else {
                if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                    news.setImgpath(null);
                    if (news.getImgpath() != null) {
                        String deletepath = news.getImgpath().substring(1, news.getImgpath().length());
                        UPLOAD.deleteFile(deletepath);
                    }
                    Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                    if ((int) upload.get("code") == 200) {
                        news.setImgpath("/pictures/" + upload.get("filename"));
                    }
                }
                news.setCreatedate(new Date());
                service.addNews(news);
                model.addAttribute("message", MessageBack.MSG(200, "添加新闻成功。"));
            }
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "添加新闻失败。"));
        }
        return "news_manage.html";
    }

    @RequestMapping("news/get/{nid}")
    @ResponseBody
    public Map<String, Object> getNews(@PathVariable Integer nid) {
        try {
            MNews news = service.getNews(nid);
            return MessageBack.DATA(200, "获取新闻信息成功", news);
        } catch (Exception e) {
            return MessageBack.MSG(200, "获取新闻信息失败");
        }
    }

    @RequestMapping("updateNews")
    public String updateNews(@ModelAttribute MNews news, Model model, @RequestParam("picture") MultipartFile file) {
        try {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                news.setImgpath(null);
                if (news.getImgpath() != null) {

                    String deletepath = news.getImgpath().substring(1, news.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    news.setImgpath("/pictures/" + upload.get("filename"));
                }
            }
            news.setCreatedate(new Date());
            service.updateNews(news);

            model.addAttribute("message", MessageBack.MSG(200, "新闻更新成功!"));
            return "news_manage.html";
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(500, "新闻更新失败!"));
            return "news_manage.html";
        }
    }

    @RequestMapping("deleteNews")
    public String deleteNews(Integer nid, Model model) {
        try {
            service.deleteNews(nid);
            model.addAttribute("message", MessageBack.MSG(200, "新闻删除成功!"));
        } catch (Exception e) {
            model.addAttribute("message", MessageBack.MSG(200, "新闻删除失败!"));
        }
        return "news_manage.html";
    }
}
