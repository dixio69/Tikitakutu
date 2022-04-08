package com.hehe.tiktaktu.tictactoe;

import com.badlogic.gdx.Game;
import com.hehe.tiktaktu.tictactoe.strategy.MinimaxStrategy;
import com.hehe.tiktaktu.tictactoe.strategy.Strategy;

public class TicTacToeGame extends Game {

    @Override
    public void create() {
//        showSettingsScreen();
        showTicTacToeScreen(new MinimaxStrategy());
    }

    public void showSettingsScreen() {
        setScreen(new SizeSettingsScreen(this));
    }

    public void showTicTacToeScreen(Strategy comStrategy) {
        setScreen(new TicTacToeScreen(this, comStrategy));
    }
}
