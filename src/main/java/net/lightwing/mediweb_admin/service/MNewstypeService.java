package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MNewsDao;
import net.lightwing.mediweb_admin.dao.MNewstypeDao;
import net.lightwing.mediweb_admin.pojo.MNewsExample;
import net.lightwing.mediweb_admin.pojo.MNewstype;
import net.lightwing.mediweb_admin.pojo.MNewstypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MNewstypeService {
    @Autowired
    private MNewstypeDao dao;

    @Autowired
    private MNewsDao newsDao;

    public List<MNewstype> getList() {
        return dao.selectByExample(null);
    }

    /**
     * 分页查询列表
     *
     * @param pageindex
     * @param pagesize
     * @return
     */
    public List<MNewstype> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        MNewstypeExample example = new MNewstypeExample();
        example.setOffset(Long.valueOf(pageindex));
        example.setLimit(pagesize);
        return dao.selectByExample(example);
    }

    public Integer getCount() {
        return (int) dao.countByExample(null);
    }

    public MNewstype getNewsType(Integer ntypeid) {
        return dao.selectByPrimaryKey(ntypeid);
    }

    public void addNewsType(MNewstype newstype) {
        dao.insertSelective(newstype);
    }

    public void updateNewsType(MNewstype newstype) {
        dao.updateByPrimaryKeySelective(newstype);
    }

    public Integer newsCountByType(Integer ntypeid) {
        MNewsExample example = new MNewsExample();
        example.createCriteria().andNtypeidEqualTo(ntypeid);
        return (int) newsDao.countByExample(example);
    }

    public void deleteNewsType(Integer ntypeid) {
        dao.deleteByPrimaryKey(ntypeid);
    }

    public MNewstype selectByName(String name) {
        return dao.selectByName(name);
    }

}
