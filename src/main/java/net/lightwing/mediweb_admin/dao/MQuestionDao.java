package net.lightwing.mediweb_admin.dao;

import java.util.List;
import net.lightwing.mediweb_admin.pojo.MQuestion;
import net.lightwing.mediweb_admin.pojo.MQuestionExample;
import org.apache.ibatis.annotations.Param;

public interface MQuestionDao {
    long countByExample(MQuestionExample example);

    int deleteByExample(MQuestionExample example);

    int deleteByPrimaryKey(Integer qid);

    int insert(MQuestion record);

    int insertSelective(MQuestion record);

    List<MQuestion> selectByExampleWithBLOBs(MQuestionExample example);

    List<MQuestion> selectByExample(MQuestionExample example);

    MQuestion selectByPrimaryKey(Integer qid);

    int updateByExampleSelective(@Param("record") MQuestion record, @Param("example") MQuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") MQuestion record, @Param("example") MQuestionExample example);

    int updateByExample(@Param("record") MQuestion record, @Param("example") MQuestionExample example);

    int updateByPrimaryKeySelective(MQuestion record);

    int updateByPrimaryKeyWithBLOBs(MQuestion record);

    int updateByPrimaryKey(MQuestion record);
}