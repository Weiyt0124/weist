package com.wyt.service;

import com.wyt.entity.UserInfo;

import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
public interface UserService {
    Boolean changePassword(Map<String, String> param);

    Boolean saveAvatar(Map<String, Object> param);

    UserInfo findUserInfo(String id);
}
