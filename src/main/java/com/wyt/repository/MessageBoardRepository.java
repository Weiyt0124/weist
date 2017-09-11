package com.wyt.repository;

import com.wyt.entity.MessageBoard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ${Weiyt} on 2017/9/11.
 */
public interface MessageBoardRepository extends JpaRepository<MessageBoard,Long> {
}
