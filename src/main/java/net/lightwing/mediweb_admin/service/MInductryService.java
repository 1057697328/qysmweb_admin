package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MInductryDao;
import net.lightwing.mediweb_admin.pojo.MDoctor;
import net.lightwing.mediweb_admin.pojo.MInductry;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MInductryService {
    @Autowired
    private MInductryDao dao;

    public int deleteByPrimaryKey(Integer iid) {
        return dao.deleteByPrimaryKey(iid);
    }

    public int insertSelective(MInductry record) {
        return dao.insertSelective(record);
    }

    public MInductry selectByPrimaryKey(Integer iid) {
        return dao.selectByPrimaryKey(iid);
    }

    public int updateByPrimaryKeySelective(MInductry record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public int count() {
        return dao.count();
    }


    public MInductry selectByName(String name) {
        return dao.selectByName(name);
    }

    public List<MInductry> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public List<MInductry> selectAll(){
        return dao.selectAll();
    }

}
