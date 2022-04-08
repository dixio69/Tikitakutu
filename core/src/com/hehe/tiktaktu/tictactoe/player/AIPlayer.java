package com.hehe.tiktaktu.tictactoe.player;

import com.hehe.tiktaktu.tictactoe.board.Board;
import com.hehe.tiktaktu.tictactoe.board.CellPosition;
import com.hehe.tiktaktu.tictactoe.strategy.*;

public class AIPlayer extends Player {

    public static final String TAG = AIPlayer.class.getName();

    Strategy strategy;

    public AIPlayer(Board board, PlayerType type, Strategy strategy) {
        super(board, type);
        this.strategy = strategy;
    }

    public CellPosition makeAIMove() {
        CellPosition bestPosition = strategy.determineBestPosition(board, this);
        board.setCell(bestPosition, playerType.getValue());
        return bestPosition;
    }
}