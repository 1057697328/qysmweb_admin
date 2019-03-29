package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MQuestionDao;
import net.lightwing.mediweb_admin.pojo.MQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class MQuestionService {

    @Autowired
    private MQuestionDao dao;

    public List<MQuestion> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public int deleteByPrimaryKey(Integer pid) {
        return dao.deleteByPrimaryKey(pid);
    }


    public int insertSelective(MQuestion record) {
        return dao.insertSelective(record);
    }

    public MQuestion selectByPrimaryKey(Integer pid) {
        return dao.selectByPrimaryKey(pid);
    }

    public int updateByPrimaryKeySelective(MQuestion record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public int count() {
        return dao.count();
    }


    public MQuestion selectByName(String name) {
        return dao.selectByName(name);
    }




}
