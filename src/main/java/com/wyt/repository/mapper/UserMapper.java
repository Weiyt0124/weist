package com.wyt.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
@Mapper
public interface UserMapper {

    @Update("UPDATE user SET password =#{password} where id = #{id}")
    int changePassword(Map<String, String> param);
    @Update("UPDATE user SET avatar =#{avatar} where id = #{id}")
    int saveAvatar(Map<String, Object> param);
}
