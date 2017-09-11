package com.wyt.service;

import java.util.List;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
public interface MessageBoardService {
    /**
     *  查询回复列表权重排序:10
     *  @Author Wyt
     *
     *  @Date 2017/9/11 14:22
     *
     */
    List<Map<String,Object>> replyList();
}
