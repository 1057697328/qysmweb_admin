package net.lightwing.mediweb_admin.dao;

import net.lightwing.mediweb_admin.pojo.MCoop;
import net.lightwing.mediweb_admin.pojo.MDoctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MDoctorDao {
    int deleteByPrimaryKey(Integer did);

    int insert(MDoctor record);

    int insertSelective(MDoctor record);

    MDoctor selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(MDoctor record);

    int updateByPrimaryKeyWithBLOBs(MDoctor record);

    int updateByPrimaryKey(MDoctor record);

    int count();


    List<MDoctor> selectWithType(@Param("pageindex") Integer pageindex, @Param("pagesize") Integer pagesize);

    MCoop selectByName(@Param("name") String name);
}