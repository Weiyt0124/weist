package com.wyt.service;

import com.wyt.entity.UserInfo;

import java.util.Map;

/**
 * @author weiyt
 */
public interface UserService {
    /**
     *  修改密码
     *  @Author Wyt
     *  @param param
     *  @return boolean
     */
    Boolean changePassword(Map<String, String> param);

    /**
     *  保存头像
     *  @Author Wyt
     *  @param  param
     *  @return boolean
     */
    Boolean saveAvatar(Map<String, Object> param);

    /**
     *  查找用户详情
     *  @Author Wyt
     *  @param  id
     *  @return UserInfo
     */
    UserInfo findUserInfo(String id);
}
