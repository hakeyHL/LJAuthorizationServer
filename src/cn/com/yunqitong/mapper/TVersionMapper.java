package cn.com.yunqitong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.yunqitong.domain.TVersion;
import cn.com.yunqitong.domain.TVersionExample;

public interface TVersionMapper {
    int countByExample(TVersionExample example);

    int deleteByExample(TVersionExample example);

    int deleteByPrimaryKey(String name);

    int insert(TVersion record);

    int insertSelective(TVersion record);

    List<TVersion> selectByExampleWithBLOBs(TVersionExample example);

    List<TVersion> selectByExample(TVersionExample example);

    TVersion selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") TVersion record, @Param("example") TVersionExample example);

    int updateByExampleWithBLOBs(@Param("record") TVersion record, @Param("example") TVersionExample example);

    int updateByExample(@Param("record") TVersion record, @Param("example") TVersionExample example);

    int updateByPrimaryKeySelective(TVersion record);

    int updateByPrimaryKeyWithBLOBs(TVersion record);

    int updateByPrimaryKey(TVersion record);
    
    TVersion selectNewVersionByPLATFORM(String platform);
}