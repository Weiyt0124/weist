package com.wyt.repository;

import com.wyt.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ${Weiyt} on 2017/9/11.
 */
public interface UserRepository  extends JpaRepository<UserInfo,Long> {
    UserInfo findById(String id);
}
