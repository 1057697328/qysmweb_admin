package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MContactDao;
import net.lightwing.mediweb_admin.pojo.MContact;
import net.lightwing.mediweb_admin.pojo.MContactExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MContactService
{
    @Autowired
    private MContactDao dao;

    public List<MContact> getList(Integer pageindex,Integer pagesize)
    {
        pageindex=(pageindex-1)*pagesize;
        MContactExample example = new MContactExample();
        example.setOffset(Long.valueOf(pageindex));
        example.setLimit(pagesize);
        return dao.selectByExample(example);
    }

    public Integer getCount()
    {
        return (int)dao.countByExample(null);
    }
}
