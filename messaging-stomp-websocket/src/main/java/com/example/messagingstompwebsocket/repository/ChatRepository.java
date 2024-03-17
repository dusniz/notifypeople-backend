package com.example.messagingstompwebsocket.repository;

import com.example.messagingstompwebsocket.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    List<Chat> findByFirstUserId(Integer firstUserId);

    List<Chat> findBySecondUserId(Integer secondUserId);

    Optional<Chat> findByFirstUserIdAndSecondUserId(Integer firstUserId, Integer secondUserId);
}
