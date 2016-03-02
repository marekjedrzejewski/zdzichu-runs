package com.wmj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by vostan on 02.03.16.
 */
public class GameScreen implements Screen {

    private GameStage stage;

    public GameScreen() {
        stage = new GameStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.act(delta);
    }


    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {

    }
}
