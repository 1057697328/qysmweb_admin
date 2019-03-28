package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MCoopDao;
import net.lightwing.mediweb_admin.pojo.MCoop;
import net.lightwing.mediweb_admin.pojo.MCoopExample;
import net.lightwing.mediweb_admin.pojo.MNews;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MCoopService {

    @Autowired
    private MCoopDao dao;

    public List<MCoop> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public int count() {
        return dao.count();
    }


    public int deleteByPrimaryKey(Integer coid) {
        return dao.deleteByPrimaryKey(coid);
    }


    public int insertSelective(MCoop record) {
        return dao.insertSelective(record);
    }

    public List<MCoop> selectByExample(MCoopExample example) {
        return dao.selectByExample(example);
    }

    public MCoop selectByPrimaryKey(Integer coid) {
        return dao.selectByPrimaryKey(coid);
    }

    public int updateByPrimaryKeySelective(MCoop record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public MCoop selectByName(String name) {
        return dao.selectByName(name);
    }

}
