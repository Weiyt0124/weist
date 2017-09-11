package com.wyt.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
@Mapper
public interface MessageBoardMapper {
    /**
     *  查询回复列表权重排序:10
     *  @Author Wyt
     *
     *  @Date 2017/9/11 14:22
     *
     */
    @Select("select u.avatar,r.* from reply as r LEFT JOIN user u on u.id = r.uid  ORDER BY r.weight desc limit 10")
    @Results({
            @Result(property="userId",column="uid"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="avatar",column="avatar"),
            @Result(property="title",column="title"),
            @Result(property="weight",column="weight"),
            @Result(property="content",column="content"),
            @Result(property="replyId",column="id")
    })
    List<Map<String,Object>> replyList();
}
