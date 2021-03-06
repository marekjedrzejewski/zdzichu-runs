package com.wmj.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.wmj.ZdzichuRuns;
import com.wmj.utils.Constants;

public class SplashScreen implements Screen {

    private ZdzichuRuns game;
    private Texture logo;
    private SpriteBatch spriteBatch;
    private long startTime;

    public static int WIDTH;
    public static int HEIGHT;

    public SplashScreen(ZdzichuRuns game) {
        this.game = game;
    }

    @Override
    public void show() {
        logo = new Texture(Gdx.files.internal(Constants.SPLASH_SCREEN_IMAGE_PATH));
        spriteBatch = new SpriteBatch();
        startTime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        handleInput();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        spriteBatch.begin();
        spriteBatch.draw(logo, 0, 0, WIDTH, HEIGHT);
        spriteBatch.end();

        if (TimeUtils.millis() > (startTime + 2000)) {
            startGame();
        }
    }

    private void handleInput() {
        if(Gdx.input.justTouched()) {
            startGame();
        }
    }

    private void startGame() {
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void resize(int width, int height) {
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
        logo.dispose();
        spriteBatch.dispose();
    }

}
