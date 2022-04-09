package com.hehe.tikitakutu.service.score;

import com.hehe.tikitakutu.entity.Cell;
import com.hehe.tikitakutu.entity.Game;
import com.hehe.tikitakutu.repository.SettingRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class OppositeDiagonalScoreProcessor extends ScoreProcessor {
    public OppositeDiagonalScoreProcessor(List<Cell> cells, Game game, SettingRepository settingRepository) {
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
//        log.info("calculate score : " + x + "," + y);
        int tempX, tempY;
        for (int i = 1; i <= winCondition; i++) {
            tempX = x + (i - 1);
            tempY = y - (i - 1);
            if (tempX > maxGrid || tempX < 1 ||
                    tempY > maxGrid || tempY < 1) {
                break;
            }
//            log.info("mbuh : " + tempX + "," + tempY);
            currentCell = findCellByXY(tempX, tempY);
            if (currentCell.getValue() == valueKey) {
//                log.info("Processing max aug : " + winCondition + " in " + currentCell.toString());
                score++;
            } else score--;
        }
        return score;
    }
}
