package com.hehe.tiktaktu.tictactoe.player;

import com.hehe.tiktaktu.tictactoe.board.Board;
import com.hehe.tiktaktu.tictactoe.board.CellPosition;
import com.hehe.tiktaktu.tictactoe.player.Player;

public class HumanPlayer extends Player {

    public static final String TAG = HumanPlayer.class.getName();

    public HumanPlayer(Board board, PlayerType type) {
        super(board, type);
    }

    public CellPosition setCellAtPosition(CellPosition position) {
        board.setCell(position, this.getPlayerType().getValue());
        return position;
    }
}