package com.hehe.tikitakutu.service;

import com.hehe.tikitakutu.dto.CellLocation;
import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.entity.GameSetting;
import com.hehe.tikitakutu.repository.CellRepository;
import com.hehe.tikitakutu.repository.SettingRepository;
import com.hehe.tikitakutu.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameControlService {
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private CellRepository cellRepository;

    @Transactional
    public void play(GameSetting gameSetting) {
        GameSetting gameSettingExisting = settingRepository.findBySessionId(gameSetting.getSessionId());
        if (gameSettingExisting == null) {
            gameSettingExisting = new GameSetting();
        }
        gameSettingExisting.setGridSize(gameSetting.getGridSize());
        gameSettingExisting.setSessionId(gameSetting.getSessionId());
        gameSettingExisting = settingRepository.save(gameSettingExisting);

        cellRepository.deleteBySessionId(gameSetting.getSessionId());

        Cell cell;
        for (int x = 1; x <= gameSettingExisting.getGridSize(); x++) {
            for (int y = 1; y <= gameSettingExisting.getGridSize(); y++) {
                cell = Cell.builder()
                        .location_x(x)
                        .location_y(y)
                        .build();
                cell.setSessionId(gameSettingExisting.getSessionId());
                cellRepository.save(cell);
            }
        }
    }

    @Transactional
    public void reset(GameSetting gameSetting) {
        GameSetting gameSettingExisting = settingRepository.findBySessionId(gameSetting.getSessionId());
        if (gameSettingExisting == null) {
            gameSettingExisting = new GameSetting();
        }
        gameSettingExisting.setGridSize(gameSetting.getGridSize());
        gameSettingExisting.setSessionId(gameSetting.getSessionId());
        gameSettingExisting = settingRepository.save(gameSettingExisting);

        cellRepository.deleteBySessionId(gameSetting.getSessionId());

        Cell cell;
        for (int x = 1; x <= gameSettingExisting.getGridSize(); x++) {
            for (int y = 1; y <= gameSettingExisting.getGridSize(); y++) {
                cell = Cell.builder()
                        .location_x(x)
                        .location_y(y)
                        .build();
                cell.setSessionId(gameSettingExisting.getSessionId());
                cellRepository.save(cell);
            }
        }
    }

    public GameSetting get(String sessionId) {
        return settingRepository.findBySessionId(sessionId);
    }
}
