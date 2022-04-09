package com.hehe.tikitakutu.service.score;

import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.entity.Game;
import com.hehe.tikitakutu.repository.SettingRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class HorizontalScoreProcessor extends ScoreProcessor {
    public HorizontalScoreProcessor(List<Cell> cells, Game game, SettingRepository settingRepository) {
        super(cells, game, settingRepository);
    }

    @Override
    public void calculateGameStatus() {
        int score;
        Cell currentCell;
        for (int y = 1; y <= game.getGridSize(); y++) {
            for (int x = 1; x <= game.getGridSize(); x++) {
                currentCell = findCellByXY(x, y);
//                log.info("Processing base : " + currentCell.toString());
                if (currentCell.getValue() != null) {
                    score = calculateScore(x, y, game.getWinCondition(), game.getGridSize());
//                    log.info("score : " + score + " | win condition : " + setting.getWinCondition());
                    if (score >= game.getWinCondition()) {
                        super.saveWinner(currentCell.getValue());
                        break;
                    }
                }
            }
        }
        super.calculateGameStatus();
    }

    public int calculateScore(int x, int y, int winCondition, int maxGrid) {
        int score = 0;
        Boolean valueKey = findCellByXY(x, y).getValue();
        Cell currentCell;
        int tempY;
        for (int i = 1; i <= winCondition; i++) {
            tempY = y + (i - 1);
            if (tempY > maxGrid || tempY < 1) {
                break;
            }
//            log.info("mbuh : " + tempX + "," + y);
            currentCell = findCellByXY(x, tempY);
            if (currentCell.getValue() == valueKey) {
//                log.info("Processing max aug : " + winCondition + " in " + currentCell.toString() + " score : " + score);
                score++;
            } else score--;
        }
        return score;
    }
}
