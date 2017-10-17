package com.wyt.service.impl;

import com.wyt.entity.UserInfo;
import com.wyt.repository.mapper.LoginUserMapper;
import com.wyt.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  @author  Wyt
 */
@Service("LoginUserServiceImpl")
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    LoginUserMapper loginUserMapper;
    @Override
    public Map<String, Object> login(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>(16);

        UserInfo userInfo = loginUserMapper.findUserById(param);
        if(userInfo == null){
            result.put("message","用户名或密码错误!");
            result.put("flag",false);
            return result;
        }
        result.put("id",userInfo.getId());
        result.put("name",userInfo.getName());
        result.put("nickname",userInfo.getNickname());
        result.put("flag",true);
        return result;
    }
}
