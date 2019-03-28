package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MInductryinfoDao;
import net.lightwing.mediweb_admin.dao.MProjectDao;
import net.lightwing.mediweb_admin.pojo.MInductryinfo;
import net.lightwing.mediweb_admin.pojo.MProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MProectService {
    @Autowired
    private MProjectDao dao;

    public List<MProject> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public int deleteByPrimaryKey(Integer pid) {
        return dao.deleteByPrimaryKey(pid);
    }


    public int insertSelective(MProject record) {
        return dao.insertSelective(record);
    }

    public MProject selectByPrimaryKey(Integer pid) {
        return dao.selectByPrimaryKey(pid);
    }

    public int updateByPrimaryKeySelective(MProject record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public int count() {
        return dao.count();
    }


    public MProject selectByName(String name) {
        return dao.selectByName(name);
    }


}
