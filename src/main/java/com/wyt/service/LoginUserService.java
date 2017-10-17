package com.wyt.service;

import java.util.Map;

/**
 * @author weiyt
 */
public interface LoginUserService {
    /**
     *  登陆
     *  @Author Wyt
     *  @param param
     *  @return map
     */
    Map<String, Object> login(Map<String, Object> param);
}
