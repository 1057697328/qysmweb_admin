package net.lightwing.mediweb_admin.dao;

import net.lightwing.mediweb_admin.pojo.MInductryinfo;
import net.lightwing.mediweb_admin.pojo.MNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MInductryinfoDao {
    int deleteByPrimaryKey(Integer iiid);

    int insert(MInductryinfo record);

    int insertSelective(MInductryinfo record);

    MInductryinfo selectByPrimaryKey(Integer iiid);

    int updateByPrimaryKeySelective(MInductryinfo record);

    int updateByPrimaryKeyWithBLOBs(MInductryinfo record);

    int updateByPrimaryKey(MInductryinfo record);

    List<MInductryinfo> selectWithType(@Param("pageindex") Integer pageindex, @Param("pagesize") Integer pagesize);

    MInductryinfo selectByName(@Param("name") String name);

    int count();
}