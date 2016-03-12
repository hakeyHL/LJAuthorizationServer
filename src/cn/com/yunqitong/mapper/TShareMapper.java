package cn.com.yunqitong.mapper;

import cn.com.yunqitong.domain.TShare;
import cn.com.yunqitong.domain.TShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TShareMapper {
    int countByExample(TShareExample example);

    int deleteByExample(TShareExample example);

    int insert(TShare record);

    int insertSelective(TShare record);

    List<TShare> selectByExample(TShareExample example);

    int updateByExampleSelective(@Param("record") TShare record, @Param("example") TShareExample example);

    int updateByExample(@Param("record") TShare record, @Param("example") TShareExample example);
}