package net.lightwing.mediweb_admin.dao;

import java.util.List;
import net.lightwing.mediweb_admin.pojo.MCoop;
import net.lightwing.mediweb_admin.pojo.MCoopExample;
import net.lightwing.mediweb_admin.pojo.MNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MCoopDao {
    int count();

    int deleteByExample(MCoopExample example);

    int deleteByPrimaryKey(Integer coid);

    int insert(MCoop record);

    int insertSelective(MCoop record);

    List<MCoop> selectByExample(MCoopExample example);

    MCoop selectByPrimaryKey(Integer coid);

    int updateByExampleSelective(@Param("record") MCoop record, @Param("example") MCoopExample example);

    int updateByExample(@Param("record") MCoop record, @Param("example") MCoopExample example);

    int updateByPrimaryKeySelective(MCoop record);

    int updateByPrimaryKey(MCoop record);

    List<MCoop> selectWithType(@Param("pageindex") Integer pageindex, @Param("pagesize") Integer pagesize);

    MCoop selectByName(@Param("name") String name);
}