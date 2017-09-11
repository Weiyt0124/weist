package com.wyt.repository.mapper;

import com.wyt.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/7/31.
 */
@Mapper
public interface LoginUserMapper {
    /***
     * 根据id查询用户
     */
    @Select("select * from user where name = #{userName} and password = #{passWord}")
    UserInfo findUserById(Map<String, Object> param);
}
