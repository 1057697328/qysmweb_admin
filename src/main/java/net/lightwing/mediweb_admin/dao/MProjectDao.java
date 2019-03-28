package net.lightwing.mediweb_admin.dao;

import net.lightwing.mediweb_admin.pojo.MInductryinfo;
import net.lightwing.mediweb_admin.pojo.MProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MProjectDao {
    int deleteByPrimaryKey(Integer pid);

    int insert(MProject record);

    int insertSelective(MProject record);

    MProject selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(MProject record);

    int updateByPrimaryKeyWithBLOBs(MProject record);

    int updateByPrimaryKey(MProject record);

    List<MProject> selectWithType(@Param("pageindex") Integer pageindex, @Param("pagesize") Integer pagesize);

    MProject selectByName(@Param("name") String name);

    int count();
}