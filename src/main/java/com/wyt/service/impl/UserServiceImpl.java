package com.wyt.service.impl;

import com.wyt.entity.UserInfo;
import com.wyt.repository.UserRepository;
import com.wyt.repository.mapper.UserMapper;
import com.wyt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean changePassword(Map<String, String> param) {
        int i = userMapper.changePassword(param);
        if(i<1){
            return false;
        }
        return true;
    }

    @Override
    public Boolean saveAvatar(Map<String, Object> param) {

            int i = userMapper.saveAvatar(param);
            if(i<1){
                return false;
            }
            return true;
    }

    @Override
    public UserInfo findUserInfo(String id) {
        UserInfo userInfo = userRepository.findById(id);
        return userInfo;
    }

}
