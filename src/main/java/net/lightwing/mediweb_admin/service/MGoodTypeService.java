package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MGoodsDao;
import net.lightwing.mediweb_admin.dao.MGoodtypeDao;
import net.lightwing.mediweb_admin.pojo.MGoods;
import net.lightwing.mediweb_admin.pojo.MGoodsExample;
import net.lightwing.mediweb_admin.pojo.MGoodtype;
import net.lightwing.mediweb_admin.pojo.MGoodtypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MGoodTypeService
{
    @Autowired
    private MGoodtypeDao dao;

    @Autowired
    private MGoodsDao goodsdao;

    public List<MGoodtype> getList()
    {
        return dao.selectByExample(null);
    }

    public List<MGoodtype> getList(Integer pageindex,Integer pagesize)
    {
        pageindex=(pageindex-1)*pagesize;
        MGoodtypeExample example = new MGoodtypeExample();
        example.setOffset(Long.valueOf(pageindex));
        example.setLimit(pagesize);
        return dao.selectByExample(example);
    }

    public Integer getCount()
    {
        return (int)dao.countByExample(null);
    }

    public void addGoodType(MGoodtype goodtype)
    {
        dao.insertSelective(goodtype);
    }

    public void updateGoodType(MGoodtype goodtype)
    {
        dao.updateByPrimaryKeySelective(goodtype);
    }

    public void deleteGoodType(Integer gtypeid)
    {
        dao.deleteByPrimaryKey(gtypeid);
    }

    public Integer goodsCountBytype(Integer gtypeid)
    {
        MGoodsExample example = new MGoodsExample();
        example.createCriteria().andGtypeidEqualTo(gtypeid);
        return (int)goodsdao.countByExample(example);
    }

    public MGoodtype getGoodtype(Integer gtypeid)
    {
        return dao.selectByPrimaryKey(gtypeid);
    }
}
