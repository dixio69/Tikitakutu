package com.hehe.tikitakutu.repository;

import com.hehe.tikitakutu.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Game, Long> {
    Game findBySessionId(String sessionId);
}
