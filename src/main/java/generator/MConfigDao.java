package generator;

import generator.MConfig;

public interface MConfigDao {
    int deleteByPrimaryKey(Integer cid);

    int insert(MConfig record);

    int insertSelective(MConfig record);

    MConfig selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(MConfig record);

    int updateByPrimaryKey(MConfig record);
}