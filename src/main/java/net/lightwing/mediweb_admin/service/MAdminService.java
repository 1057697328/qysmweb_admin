package net.lightwing.mediweb_admin.service;

import net.lightwing.mediweb_admin.dao.MAdminDao;
import net.lightwing.mediweb_admin.pojo.MAdmin;
import net.lightwing.mediweb_admin.pojo.MAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class MAdminService
{
    @Autowired
    private MAdminDao dao;

    /**
     * 登录
     * @param admin
     * @return
     */
    public Boolean login(MAdmin admin)
    {
        return dao.login(admin)>0;
    }

    /**
     * 根据用户名获取管理员信息
     * @param account
     * @return
     */
    public MAdmin getAdminByAccount(String account)
    {
        MAdminExample example = new MAdminExample();
        example.createCriteria().andAccountEqualTo(account);
        return dao.selectByExample(example).get(0);
    }



}
