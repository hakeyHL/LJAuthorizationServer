package cn.com.yunqitong.mapper;

import cn.com.yunqitong.domain.TSuggest;
import cn.com.yunqitong.domain.TSuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSuggestMapper {
    int countByExample(TSuggestExample example);

    int deleteByExample(TSuggestExample example);

    int deleteByPrimaryKey(String id);

    int insert(TSuggest record);

    int insertSelective(TSuggest record);

    List<TSuggest> selectByExample(TSuggestExample example);

    TSuggest selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TSuggest record, @Param("example") TSuggestExample example);

    int updateByExample(@Param("record") TSuggest record, @Param("example") TSuggestExample example);

    int updateByPrimaryKeySelective(TSuggest record);

    int updateByPrimaryKey(TSuggest record);
}