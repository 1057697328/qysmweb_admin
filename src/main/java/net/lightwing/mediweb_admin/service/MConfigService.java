package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MConfigDao;
import net.lightwing.mediweb_admin.pojo.MConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
