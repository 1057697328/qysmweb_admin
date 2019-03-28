package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MBannerDao;
import net.lightwing.mediweb_admin.pojo.MBanner;
import net.lightwing.mediweb_admin.pojo.MBannerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MBannerService
{
    @Autowired
    private MBannerDao dao;

    /**
     * 分页获取轮播列表
     * @param pageindex
     * @param pagesize
     * @return
     */
    public List<MBanner> getList(Integer pageindex,Integer pagesize)
    {
        pageindex=(pageindex-1)*pagesize;
        MBannerExample example = new MBannerExample();
        example.setOffset(Long.valueOf(pageindex));
        example.setLimit(pagesize);
        return dao.selectByExample(example);
    }

    public Integer getCount()
    {
        return (int) dao.countByExample(null);
    }

    public MBanner getBanner(Integer bid)
    {
        return dao.selectByPrimaryKey(bid);
    }

    public void updateBanner(MBanner banner)
    {
        dao.updateByPrimaryKeySelective(banner);
    }

    public void addBanner(MBanner banner)
    {
        dao.insertSelective(banner);
    }

    public void deleteBanner(Integer bid)
    {
        dao.deleteByPrimaryKey(bid);
    }

}
