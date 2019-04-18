package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.common.UPLOAD;
import net.lightwing.mediweb_admin.pojo.MGoods;
import net.lightwing.mediweb_admin.pojo.MGoodtype;
import net.lightwing.mediweb_admin.service.MGoodTypeService;
import net.lightwing.mediweb_admin.service.MGoodsService;
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
public class MGoodsController
{

    @Autowired
    private MGoodsService service;

    @Autowired
    private MGoodTypeService typeService;

    @RequestMapping("goods_manage.html")
    public String GoodsManage(@RequestParam(name = "pageindex",required = false) Integer pageindex, Model model, HttpSession session)
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
                List<MGoods> list = service.getList(pageindex, Constant.PAGESIZE);
                List<MGoodtype> goodtypeList = typeService.getList();
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.getCount(), list);
                model.addAttribute("pageData", bean);
                model.addAttribute("goodtypes",goodtypeList);
            }
            catch (Exception e)
            {
                model.addAttribute("message",MessageBack.MSG(500,"展示商品信息获取失败"));
            }
            return "goods_manage.html";
        }
    }

    @RequestMapping("addGoods")
    public String addGoods(@ModelAttribute MGoods goods, @RequestParam("picture") MultipartFile file,Model model)
    {
        try
        {
            if(StringUtils.isNotBlank(file.getOriginalFilename()))
            {
                goods.setImgpath(null);
                if(goods.getImgpath()!=null) {

                    String deletepath = goods.getImgpath().substring(1, goods.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if((int)upload.get("code")==200)
                {
                    goods.setImgpath("/pictures/"+upload.get("filename"));
                }
            }
            service.addGoods(goods);

            model.addAttribute("message",MessageBack.MSG(200,"展示商品添加成功!"));
            return "goods_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"展示商品添加失败!"));
            return "goods_manage.html";
        }
    }

    @RequestMapping("/goods/get/{gid}")
    @ResponseBody
    public Map<String,Object> getGoods(@PathVariable Integer gid)
    {
        try
        {
            MGoods data = service.getGoods(gid);
            return MessageBack.DATA(200,"展示商品信息获取成功。",data);
        }
        catch (Exception e)
        {
            return MessageBack.MSG(500,"展示商品信息获取失败");
        }
    }

    @RequestMapping("updateGoods")
    public String updateGoods(@ModelAttribute MGoods goods,@RequestParam("picture") MultipartFile file,Model model)
    {
        try
        {
            if(StringUtils.isNotBlank(file.getOriginalFilename()))
            {
                goods.setImgpath(null);
                if(goods.getImgpath()!=null) {

                    String deletepath = goods.getImgpath().substring(1, goods.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if((int)upload.get("code")==200)
                {
                    goods.setImgpath("/pictures/"+upload.get("filename"));
                }
            }
            service.updateGoods(goods);

            model.addAttribute("message",MessageBack.MSG(200,"展示商品更新成功!"));
            return "goods_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"展示商品更新失败!"));
            return "goods_manage.html";
        }
    }



    @RequestMapping("deleteGoods")
    public String deleteBanner(Integer gid,Model model)
    {
        try
        {
            service.deleteGoods(gid);
            model.addAttribute("message",MessageBack.MSG(200,"展示商品删除成功!"));
            return "goods_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(500,"展示商品删除失败!"));
            return "goods_manage.html";
        }
    }

}
