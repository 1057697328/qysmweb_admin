package net.lightwing.mediweb_admin.dao;

import java.util.List;
import net.lightwing.mediweb_admin.pojo.MAdmin;
import net.lightwing.mediweb_admin.pojo.MAdminExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MAdminDao {
    long countByExample(MAdminExample example);

    int deleteByExample(MAdminExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(MAdmin record);

    int insertSelective(MAdmin record);

    List<MAdmin> selectByExample(MAdminExample example);

    MAdmin selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") MAdmin record, @Param("example") MAdminExample example);

    int updateByExample(@Param("record") MAdmin record, @Param("example") MAdminExample example);

    int updateByPrimaryKeySelective(MAdmin record);

    int updateByPrimaryKey(MAdmin record);

    int login(@Param("record") MAdmin record);
}