package com.hehe.tiktaktu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {

    final Tikitakutu game;
    private SpriteBatch spriteBatch;
    private FitViewport fitViewport;

    public MainMenuScreen(final Tikitakutu gam) {
        game = gam;
        spriteBatch = new SpriteBatch();

//        float maxStageSize = Gdx.graphics.getHeight();
        float maxStageSize = 1080;
        fitViewport = new FitViewport(maxStageSize, maxStageSize);
//        camera = new OrthographicCamera();
//        camera.(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        fitViewport.apply();

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
//            game.setScreen(new GameScreen(game));
            dispose();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            System.exit(0);
        }
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}