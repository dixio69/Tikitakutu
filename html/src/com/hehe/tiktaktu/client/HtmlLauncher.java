package com.hehe.tiktaktu.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.hehe.tiktaktu.MainMenuScreen;
import com.hehe.tiktaktu.Tikitakutu;
import com.hehe.tiktaktu.tictactoe.TicTacToeGame;

public class HtmlLauncher extends GwtApplication {

//        @Override
//        public GwtApplicationConfiguration getConfig () {
//                // Resizable application, uses available space in browser
//                return new GwtApplicationConfiguration(true);
//                // Fixed size application:
////                return new GwtApplicationConfiguration(800, 480);
//        }
//
//        @Override
//        public ApplicationListener createApplicationListener () {
//                return new Tikitakutu();
//        }

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new TicTacToeGame();
        }
}