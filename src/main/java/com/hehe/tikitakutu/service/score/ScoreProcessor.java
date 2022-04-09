package com.hehe.tikitakutu.service.score;

import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.entity.Game;
import com.hehe.tikitakutu.repository.SettingRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public abstract class ScoreProcessor {
    protected List<Cell> cells;
    protected Game game;
    protected SettingRepository settingRepository;

    public ScoreProcessor(List<Cell> cells, Game game, SettingRepository settingRepository) {
        this.cells = cells;
        this.game = game;
        this.settingRepository = settingRepository;
    }

    public void calculateGameStatus() {
        List<Cell> availableCell = cells.stream().filter(d -> d.getValue() == null).collect(Collectors.toList());
        if (availableCell.size() < 1) {
            this.game.setFinished(true);
            this.game = settingRepository.save(game);
        }
    }

    public abstract int calculateScore(int x, int y, int winCondition, int maxGrid);

    public Cell findCellByXY(int x, int y) {
        for (Cell c : cells) {
            if (c.getLocation_x() == x && c.getLocation_y() == y)
                return c;
        }
        return null;
    }

    protected void saveWinner(Boolean winner) {
        if (winner != null) {
            log.info(" ---------WINNER------------- ");
            log.info(" ---------" + winner + "------------- ");
            this.game.setWinner(winner);
            this.game.setFinished(true);
            this.game = settingRepository.save(game);
        }
    }
}
