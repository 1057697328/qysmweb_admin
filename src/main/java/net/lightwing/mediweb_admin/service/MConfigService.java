package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MConfigDao;
import net.lightwing.mediweb_admin.dao.MConfigDao;
import net.lightwing.mediweb_admin.pojo.MConfig;
import net.lightwing.mediweb_admin.pojo.MConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MConfigService
{
    @Autowired
    private MConfigDao dao;

    public MConfig getConfig(Integer cid)
    {
        return dao.selectByPrimaryKey(cid);
    }

    public void updateConfig(MConfig config)
    {
        dao.updateByPrimaryKeyWithBLOBs(config);
    }

    public List<MConfig> getList(Integer pageindex, Integer pagesize) {
        pageindex = (pageindex - 1) * pagesize;
        return dao.selectWithType(pageindex, pagesize);
    }

    public int deleteByPrimaryKey(Integer pid) {
        return dao.deleteByPrimaryKey(pid);
    }


    public int insertSelective(MConfig record) {
        return dao.insertSelective(record);
    }

    public MConfig selectByPrimaryKey(Integer pid) {
        return dao.selectByPrimaryKey(pid);
    }

    public int updateByPrimaryKeySelective(MConfig record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    public int count() {
        return dao.count();
    }

}
