package com.hehe.tiktaktu.tictactoe.strategy;

import com.badlogic.gdx.math.MathUtils;
import com.hehe.tiktaktu.tictactoe.board.*;
import com.hehe.tiktaktu.tictactoe.player.Player;

import java.util.List;

public class RandomStrategy implements Strategy {

    public static final String TAG = RandomStrategy.class.getName();

    public CellPosition determineBestPosition(Board board, Player forPlayer) {
        List<CellPosition> availableCells = board.emptyCellPositions();
        int randomIndex = MathUtils.random(0, availableCells.size() - 1);
        return availableCells.get(randomIndex);
    }
}
