package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MInductryinfoDao;
import net.lightwing.mediweb_admin.dao.MNewsDao;
import net.lightwing.mediweb_admin.pojo.MInductryinfo;
import net.lightwing.mediweb_admin.pojo.MNews;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MIductrInfoService {
    @Autowired
    private MInductryinfoDao dao;

    public List<MInductryinfo> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public int deleteByPrimaryKey(Integer iiid) {
        return dao.deleteByPrimaryKey(iiid);
    }


    public int insertSelective(MInductryinfo record) {
        return dao.insertSelective(record);
    }

    public MInductryinfo selectByPrimaryKey(Integer iiid) {
        return dao.selectByPrimaryKey(iiid);
    }

    public int updateByPrimaryKeySelective(MInductryinfo record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public int count() {
        return dao.count();
    }


    public MInductryinfo selectByName(String name) {
        return dao.selectByName(name);
    }


}
