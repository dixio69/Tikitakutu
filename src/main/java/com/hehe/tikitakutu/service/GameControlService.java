package com.hehe.tikitakutu.service;

import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.entity.Game;
import com.hehe.tikitakutu.repository.CellRepository;
import com.hehe.tikitakutu.repository.SettingRepository;
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
    public void play(Game game) {
        Game gameExisting = settingRepository.findBySessionId(game.getSessionId());
        if (gameExisting == null) {
            gameExisting = new Game();
        }
        game.setId(gameExisting.getId());
        gameExisting = settingRepository.save(game);

        cellRepository.deleteBySessionId(game.getSessionId());

        Cell cell;
        for (int x = 1; x <= gameExisting.getGridSize(); x++) {
            for (int y = 1; y <= gameExisting.getGridSize(); y++) {
                cell = Cell.builder()
                        .location_x(x)
                        .location_y(y)
                        .build();
                cell.setSessionId(gameExisting.getSessionId());
                cellRepository.save(cell);
            }
        }
    }

    @Transactional
    public void reset(Game game) {
        Game gameExisting = settingRepository.findBySessionId(game.getSessionId());
        if (gameExisting == null) {
            gameExisting = new Game();
        }
        gameExisting.setGridSize(game.getGridSize());
        gameExisting.setSessionId(game.getSessionId());
        gameExisting = settingRepository.save(gameExisting);

        cellRepository.deleteBySessionId(game.getSessionId());

        Cell cell;
        for (int x = 1; x <= gameExisting.getGridSize(); x++) {
            for (int y = 1; y <= gameExisting.getGridSize(); y++) {
                cell = Cell.builder()
                        .location_x(x)
                        .location_y(y)
                        .build();
                cell.setSessionId(gameExisting.getSessionId());
                cellRepository.save(cell);
            }
        }
    }

    public Game get(String sessionId) {
        return settingRepository.findBySessionId(sessionId);
    }
}
