package cn.com.yunqitong.mapper;

import cn.com.yunqitong.domain.TCallRecord;
import cn.com.yunqitong.domain.TCallRecordExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TCallRecordMapper {
    int countByExample(TCallRecordExample example);

    int deleteByExample(TCallRecordExample example);

    int deleteByPrimaryKey(String callid);

    int insert(TCallRecord record);

    int insertSelective(TCallRecord record);

    List<TCallRecord> selectByExample(TCallRecordExample example);

    TCallRecord selectByPrimaryKey(String callid);

    int updateByExampleSelective(@Param("record") TCallRecord record, @Param("example") TCallRecordExample example);

    int updateByExample(@Param("record") TCallRecord record, @Param("example") TCallRecordExample example);

    int updateByPrimaryKeySelective(TCallRecord record);

    int updateByPrimaryKey(TCallRecord record);
    
    int updatecalledstatusByMeetingIdAndUserId(Map map);
    
    int updatecallerstatusByMeetingIdAndUserId(Map map);
}