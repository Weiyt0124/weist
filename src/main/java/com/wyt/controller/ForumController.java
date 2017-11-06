package com.wyt.controller;

import com.wyt.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author weiyt
 */
@Controller
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;


    /**
     *  查询论坛列表
     *  @Author Wyt
     *
     *  @Date 2017/9/11 14:22
     *
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> findList(){
        return forumService.findList();
    }

}
