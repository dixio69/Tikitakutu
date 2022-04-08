package com.hehe.tiktaktu.tictactoe.strategy;

import com.hehe.tiktaktu.tictactoe.board.*;
import com.hehe.tiktaktu.tictactoe.player.Player;

public interface Strategy {
    CellPosition determineBestPosition(Board board, Player forPlayer);
}
