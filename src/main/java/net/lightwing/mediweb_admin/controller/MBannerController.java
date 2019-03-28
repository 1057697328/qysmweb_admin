package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.common.UPLOAD;
import net.lightwing.mediweb_admin.pojo.MBanner;
import net.lightwing.mediweb_admin.service.MBannerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
public class MBannerController
{

    @Autowired
    private MBannerService service;

    @RequestMapping("banner_manage.html")
    public ModelAndView BannerManage(HttpSession session,@RequestParam(name = "pageindex",required = false) Integer pageindex)
    {
        ModelAndView mav = new ModelAndView();
        if(session.getAttribute("ADMIN")==null)
        {
            mav.addAllObjects(MessageBack.MSG(500,"请您重新登录。"));
            mav.setViewName("login.html");
        }
        else
        {
            if(pageindex==null)
            {
                pageindex=1;
            }
            List<MBanner> list = service.getList(pageindex,Constant.PAGESIZE);
            PageBean bean = new PageBean(pageindex, Constant.PAGESIZE,service.getCount(),list);
            mav.addObject("pageData",bean);
            mav.setViewName("banner_manage.html");
        }
        return mav;
    }

    @RequestMapping("/banner/get/{bid}")
    @ResponseBody
    public Map<String,Object> getBanner(@PathVariable Integer bid)
    {
        try
        {
            MBanner data = service.getBanner(bid);
            return MessageBack.DATA(200,"轮播信息获取成功",data);
        }
        catch (Exception e)
        {
            return MessageBack.MSG(500,"系统异常。");
        }
    }


    @RequestMapping("/updateBanner")
    public String updateBanner(@RequestParam("bannerpic") MultipartFile file, @ModelAttribute MBanner banner, Model model, HttpServletRequest request)
    {
        try
        {
            if(StringUtils.isNotBlank(file.getOriginalFilename()))
            {
                banner.setImgpath(null);
                if(banner.getImgpath()!=null) {

                    String deletepath = banner.getImgpath().substring(1, banner.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if((int)upload.get("code")==200)
                {
                    banner.setImgpath("/pictures/"+upload.get("filename"));
                }
            }
            service.updateBanner(banner);

            model.addAttribute("message",MessageBack.MSG(200,"轮播更新成功!"));
            return "banner_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"轮播更新失败!"));
            return "banner_manage.html";
        }
    }


    @RequestMapping("/addBanner")
    public String addBanner(@RequestParam("bannerpic") MultipartFile file,@ModelAttribute MBanner banner, Model model)
    {
        try
        {
            if(StringUtils.isNotBlank(file.getOriginalFilename()))
            {
                banner.setImgpath(null);
                if(banner.getImgpath()!=null) {

                    String deletepath = banner.getImgpath().substring(1, banner.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if((int)upload.get("code")==200)
                {
                    banner.setImgpath("/pictures/"+upload.get("filename"));
                }
            }
            service.addBanner(banner);

            model.addAttribute("message",MessageBack.MSG(200,"轮播添加成功!"));
            return "banner_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"轮播添加失败!"));
            return "banner_manage.html";
        }
    }

    @RequestMapping("deleteBanner")
    public String deleteBanner(Integer bid,Model model)
    {
        try
        {
            service.deleteBanner(bid);
            model.addAttribute("message",MessageBack.MSG(200,"轮播删除成功!"));
            return "banner_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"轮播删除失败!"));
            return "banner_manage.html";
        }
    }



}
