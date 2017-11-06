package com.wyt.service.impl;

import com.wyt.entity.Forum;
import com.wyt.repository.ForumRepository;
import com.wyt.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author  Wyt
 */
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    ForumRepository forumRepository;

    @Override
    public Map<String, Object> findList() {
        Map<String, Object> result = new HashMap<>(16);
        List<Forum> forum = forumRepository.findAll();
        result.put("code",1);
        result.put("forum",forum);
        return result;
    }
}
