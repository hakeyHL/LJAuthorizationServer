package cn.com.yunqitong.mapper;

import cn.com.yunqitong.domain.TFee;
import cn.com.yunqitong.domain.TFeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFeeMapper {
    int countByExample(TFeeExample example);

    int deleteByExample(TFeeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TFee record);

    int insertSelective(TFee record);

    List<TFee> selectByExample(TFeeExample example);

    TFee selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TFee record, @Param("example") TFeeExample example);

    int updateByExample(@Param("record") TFee record, @Param("example") TFeeExample example);

    int updateByPrimaryKeySelective(TFee record);

    int updateByPrimaryKey(TFee record);
}