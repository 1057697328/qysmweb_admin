package net.lightwing.mediweb_admin.controller;

import net.lightwing.mediweb_admin.common.Constant;
import net.lightwing.mediweb_admin.common.MessageBack;
import net.lightwing.mediweb_admin.common.PageBean;
import net.lightwing.mediweb_admin.pojo.MGoods;
import net.lightwing.mediweb_admin.pojo.MGoodtype;
import net.lightwing.mediweb_admin.service.MGoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class MGoodTypeController
{
    @Autowired
    private MGoodTypeService service;

    @RequestMapping("goodtype_manage.html")
    public String GoodtypeManage(Model model, HttpSession session,@RequestParam(name = "pageindex",required = false) Integer pageindex)
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
                List<MGoodtype> list = service.getList(pageindex, Constant.PAGESIZE);
                PageBean bean = new PageBean(pageindex, Constant.PAGESIZE, service.getCount(), list);
                model.addAttribute("pageData", bean);
            }
            catch (Exception e)
            {
                model.addAttribute("message",MessageBack.MSG(500,"展示商品类型信息获取失败"));
            }
            return "goodtype_manage.html";
        }
    }

    @RequestMapping("addGoodtype")
    public String addGoodType(@ModelAttribute MGoodtype goodtype,Model model)
    {
        try
        {
            service.addGoodType(goodtype);
            model.addAttribute("message",MessageBack.MSG(200,"展示商品类型添加成功!"));
            return "goodtype_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(200,"展示商品类型添加失败!"));
            return "goodtype_manage.html";
        }
    }

    @RequestMapping("updateGoodtype")
    public String updateGoodtype(@ModelAttribute MGoodtype goodtype,Model model)
    {
        try
        {
            service.updateGoodType(goodtype);
            model.addAttribute("message",MessageBack.MSG(200,"展示商品类型信息修改成功!"));
            return "goodtype_manage.html";
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(200,"展示商品类型信息修改失败!"));
            return "goodtype_manage.html";
        }
    }

    @RequestMapping("deleteGoodtype")
    public String deleteGoodtype(Integer gtypeid,Model model)
    {
        try
        {
            if(service.goodsCountBytype(gtypeid)>0)
            {
                model.addAttribute("message",MessageBack.MSG(200,"删除商品类型失败,该商品类型下还有展示商品信息,请删除所有该类型的展示商品后再试！"));
                return "goodtype_manage.html";
            }
            else {
                service.deleteGoodType(gtypeid);
                model.addAttribute("message", MessageBack.MSG(200, "删除商品类型成功!"));
                return "goodtype_manage.html";
            }
        }
        catch (Exception e)
        {
            model.addAttribute("message",MessageBack.MSG(200,"删除商品类型失败!"));
            return "goodtype_manage.html";
        }
    }


    @RequestMapping("/goodtype/get/{gtypeid}")
    @ResponseBody
    public Map<String,Object> getGoodtype(@PathVariable Integer gtypeid)
    {
        try
        {
            MGoodtype data = service.getGoodtype(gtypeid);
            return MessageBack.DATA(200,"展示商品类型信息获取成功。",data);
        }
        catch (Exception e)
        {
            return MessageBack.MSG(500,"展示商品类型获取失败");
        }
    }

}
