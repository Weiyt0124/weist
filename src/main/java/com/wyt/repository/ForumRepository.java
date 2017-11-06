package com.wyt.repository;

import com.wyt.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weiyt
 */
public interface ForumRepository extends JpaRepository<Forum,Long> {
}
