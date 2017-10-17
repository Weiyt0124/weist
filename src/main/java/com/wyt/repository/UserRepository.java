package com.wyt.repository;

import com.wyt.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weiyt
 */
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findById(String id);
}
