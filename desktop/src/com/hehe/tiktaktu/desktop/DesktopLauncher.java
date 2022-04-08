package com.hehe.tiktaktu.desktop;

//import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hehe.tiktaktu.MainMenuScreen;
import com.hehe.tiktaktu.Tikitakutu;
import com.hehe.tiktaktu.tictactoe.TicTacToeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Tikitakatu");
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setWindowedMode(800, 450);
//		config.height = 450;
//		config.setMaximized(true);
		new Lwjgl3Application(new TicTacToeGame(), config);
	}
}
