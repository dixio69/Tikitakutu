package com.hehe.tikitakutu.repository;

import com.hehe.tikitakutu.entity.GameSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<GameSetting, Long> {
    GameSetting findBySessionId(String sessionId);
}
