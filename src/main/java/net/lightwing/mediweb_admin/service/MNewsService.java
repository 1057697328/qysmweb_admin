package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MNewsDao;
import net.lightwing.mediweb_admin.pojo.MNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MNewsService
{
    @Autowired
    private MNewsDao dao;

    public List<MNews> getList(Integer pageindex,Integer pagesize)
    {
        pageindex=(pageindex-1)*pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public MNews getNews(Integer nid)
    {
        return dao.selectByPrimaryKey(nid);
    }

    public Integer getCount()
    {
        return (int)dao.countByExample(null);
    }

    public void addNews(MNews news)
    {
        dao.insertSelective(news);
    }

    public void updateNews(MNews news)
    {
        dao.updateByPrimaryKeyWithBLOBs(news);
    }

    public void deleteNews(Integer nid)
    {
        dao.deleteByPrimaryKey(nid);
    }

    public MNews selectByName(String name){
        return dao.selectByName(name);
    }


}
