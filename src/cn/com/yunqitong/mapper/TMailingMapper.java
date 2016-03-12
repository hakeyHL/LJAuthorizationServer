package cn.com.yunqitong.mapper;

import cn.com.yunqitong.domain.TMailing;
import cn.com.yunqitong.domain.TMailingExample;
import cn.com.yunqitong.vo.PhoneVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMailingMapper {
    int countByExample(TMailingExample example);

    int deleteByExample(TMailingExample example);

    int insert(TMailing record);

    int insertSelective(TMailing record);

    List<TMailing> selectByExample(TMailingExample example);
    
    List<PhoneVo> selectUserMailingList(@Param("mccountid")String mccountid);

    int updateByExampleSelective(@Param("record") TMailing record, @Param("example") TMailingExample example);

    int updateByExample(@Param("record") TMailing record, @Param("example") TMailingExample example);
}