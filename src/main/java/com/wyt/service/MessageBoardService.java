package com.wyt.service;

import java.util.List;
import java.util.Map;

/**
 * @author weiyt
 */
public interface MessageBoardService {
    /**
     *  查询回复列表权重排序:10
     *  @Author Wyt
     *
     *  @Date 2017/9/11 14:22
     *  @return map
     */
    List<Map<String,Object>> replyList();
}
