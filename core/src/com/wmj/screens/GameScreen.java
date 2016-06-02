package com.wmj.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.wmj.ZdzichuRuns;
import com.wmj.stages.GameStage;
import com.wmj.utils.Constants;
import com.wmj.utils.WorldUtils;

public class GameScreen implements Screen {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private ZdzichuRuns game;
    private GameStage stage;

    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer mDebugRenderer;

    public GameScreen(ZdzichuRuns game) {
        this.game = game;

        camera = new OrthographicCamera(25, 15);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();

        world = WorldUtils.createWorld();
        stage = new GameStage(world);
        mDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.act(delta);
//        mDebugRenderer.render(world, camera.combined);
    }

    private void renderPhysics(){

    }

    @Override
    public void resize(int width, int height) {

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
