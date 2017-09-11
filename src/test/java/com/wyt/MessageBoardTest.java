package com.wyt;

import com.wyt.service.MessageBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageBoardTest {
    @Autowired
    MessageBoardService messageBoardService;
    @Test
    public void replyList(){
        List<Map<String,Object>> messageBoards = messageBoardService.replyList();
        System.out.println(messageBoards);
    }

}
