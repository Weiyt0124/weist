package com.wyt.service.impl;

import com.wyt.repository.MessageBoardRepository;
import com.wyt.repository.mapper.MessageBoardMapper;
import com.wyt.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
@Service
public class MessageBoardServiceImpl implements MessageBoardService {

    @Autowired
    MessageBoardMapper messageBoardMapper;

    @Autowired
    MessageBoardRepository messageBoardRepository;


    /**
     *  查询回复列表权重排序:10
     *  @Author Wyt
     *
     *  @Date 2017/9/11 14:22
     *
     */
    @Override
    public List<Map<String,Object>> replyList() {
        List<Map<String,Object>> messageBoards = messageBoardMapper.replyList();
        return messageBoards;
    }
}
