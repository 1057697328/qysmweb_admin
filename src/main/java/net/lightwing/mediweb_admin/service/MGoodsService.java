package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MGoodsDao;
import net.lightwing.mediweb_admin.pojo.MGoods;
import net.lightwing.mediweb_admin.pojo.MGoodsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MGoodsService
{
    @Autowired
    private MGoodsDao dao;

    /**
     *
     * @param pageindex 当前页数
     * @param pagesize 每页显示记录数,暂时取Constant.PAGESIZE固定值
     * @return 分页获取商品列表信息
     */
    public List<MGoods> getList(Integer pageindex,Integer pagesize)
    {
        pageindex=(pageindex-1)*pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public Integer getCount()
    {
        return (int)dao.countByExample(null);
    }

    public void addGoods(MGoods goods)
    {
        dao.insertSelective(goods);
    }

    public MGoods getGoods(Integer gid)
    {
        MGoodsExample example = new MGoodsExample();
        example.createCriteria().andGidEqualTo(gid);
        return dao.selectByExample(example).get(0);
    }

    public void updateGoods(MGoods goods)
    {
        dao.updateByPrimaryKeySelective(goods);
    }

    public void deleteGoods(Integer gid)
    {
        dao.deleteByPrimaryKey(gid);
    }
}
