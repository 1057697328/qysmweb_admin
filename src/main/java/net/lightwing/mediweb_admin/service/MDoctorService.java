package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MDoctorDao;
import net.lightwing.mediweb_admin.pojo.MCoop;
import net.lightwing.mediweb_admin.pojo.MDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MDoctorService {

    @Autowired
    private MDoctorDao dao;

    public int deleteByPrimaryKey(Integer did) {
        return dao.deleteByPrimaryKey(did);
    }

    public int insertSelective(MDoctor record) {
        return dao.insertSelective(record);
    }

    public MDoctor selectByPrimaryKey(Integer did) {
        return dao.selectByPrimaryKey(did);
    }

    public int updateByPrimaryKeySelective(MDoctor record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public int count() {
        return dao.count();
    }


    public List<MDoctor> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public MCoop selectByName(String name) {
        return dao.selectByName(name);
    }
}
