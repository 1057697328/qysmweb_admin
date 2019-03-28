package net.lightwing.mediweb_admin.dao;

import net.lightwing.mediweb_admin.pojo.MInductry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MInductryDao {
    int deleteByPrimaryKey(Integer iid);

    int insert(MInductry record);

    int insertSelective(MInductry record);

    MInductry selectByPrimaryKey(Integer iid);

    int updateByPrimaryKeySelective(MInductry record);

    int updateByPrimaryKey(MInductry record);

    int count();


    List<MInductry> selectWithType(@Param("pageindex") Integer pageindex, @Param("pagesize") Integer pagesize);

    MInductry selectByName(@Param("name") String name);

    List<MInductry> selectAll();

}