package cn.com.yunqitong.mapper;

import cn.com.yunqitong.domain.TValidate;
import cn.com.yunqitong.domain.TValidateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TValidateMapper {
    int countByExample(TValidateExample example);

    int deleteByExample(TValidateExample example);

    int deleteByPrimaryKey(String phone);

    int insert(TValidate record);

    int insertSelective(TValidate record);

    List<TValidate> selectByExample(TValidateExample example);

    TValidate selectByPrimaryKey(String phone);

    int updateByExampleSelective(@Param("record") TValidate record, @Param("example") TValidateExample example);

    int updateByExample(@Param("record") TValidate record, @Param("example") TValidateExample example);

    int updateByPrimaryKeySelective(TValidate record);

    int updateByPrimaryKey(TValidate record);
}