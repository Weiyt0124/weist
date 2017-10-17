package com.wyt.repository;

import com.wyt.entity.MessageBoard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author weiyt
 */
public interface MessageBoardRepository extends JpaRepository<MessageBoard,Long> {
}
