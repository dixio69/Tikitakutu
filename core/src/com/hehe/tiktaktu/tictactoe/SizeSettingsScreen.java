package com.hehe.tiktaktu.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hehe.tiktaktu.tictactoe.strategy.MinimaxStrategy;
import com.hehe.tiktaktu.tictactoe.strategy.RandomStrategy;
import com.hehe.tiktaktu.tictactoe.strategy.RuleBasedStrategy;

public class SizeSettingsScreen extends InputAdapter implements Screen {

    public static final String TAG = SizeSettingsScreen.class.getName();

    TicTacToeGame game;

    ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;

    BitmapFont font;

    public SizeSettingsScreen(TicTacToeGame game) {
        this.game = game;
    }

    Texture upArrowImage;
    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        Texture texture = new Texture(Gdx.files.internal("up-arrow.png"));

        Image image1 = new Image(texture);
        image1.setPosition(Gdx.graphics.getWidth()/3-image1.getWidth()/2,Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
        stage.addActor(image1);

        Image image2 = new Image(texture);
        image2.setPosition(Gdx.graphics.getWidth()*2/3-image2.getWidth()/2,Gdx.graphics.getHeight()*2/3-image2.getHeight()/2);
        image2.setOrigin(image2.getWidth()/2,image2.getHeight()/2);
        image2.rotateBy(45);
        stage.addActor(image2);

        Image image3 = new Image(texture);
        image3.setSize(texture.getWidth()/2,texture.getHeight()/2);
        image3.setPosition(Gdx.graphics.getWidth()/3-image3.getWidth()/2,Gdx.graphics.getHeight()/3-image3.getHeight());
        stage.addActor(image3);

        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,texture.getWidth()*8,texture.getHeight()*4);
        Image image4 = new Image(textureRegion);
        image4.setSize(200,100);
        image4.setPosition(Gdx.graphics.getWidth()*2/3-image4.getWidth()/2,Gdx.graphics.getHeight()/3-image4.getHeight());
        stage.addActor(image4);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
//        super.re
//        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
//        batch.dispose();
//        font.dispose();
//        renderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//
//        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));
//
//        if (worldTouch.dst(Constants.RANDOM_CENTER) < Constants.SETTINGS_BUBBLE_RADIUS) {
//            game.showTicTacToeScreen(new RandomStrategy());
//        }
//
//        if (worldTouch.dst(Constants.RULE_BASED_CENTER) < Constants.SETTINGS_BUBBLE_RADIUS) {
//            game.showTicTacToeScreen(new RuleBasedStrategy());
//        }
//
//        if (worldTouch.dst(Constants.MINIMAX_CENTER) < Constants.SETTINGS_BUBBLE_RADIUS) {
//            game.showTicTacToeScreen(new MinimaxStrategy());
//        }

        return true;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}