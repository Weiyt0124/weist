package com.wyt.controller;

import com.wyt.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
@Controller
@RequestMapping("messageBoard")
public class MessageBoardController {
    @Autowired
    private MessageBoardService messageBoardService;

    /**
     *  查询回复列表权重排序:5
     *  @Author Wyt
     *
     *  @Date 2017/9/11 14:22
     *
     */
    @GetMapping("/replyList")
    @ResponseBody
    public List<Map<String,Object>> replyList(){
        return messageBoardService.replyList();
    }


}
