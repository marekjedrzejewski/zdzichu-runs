package com.wmj.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.wmj.actors.Background;
import com.wmj.actors.Enemy;
import com.wmj.actors.Ground;
import com.wmj.actors.Runner;
import com.wmj.actors.menu.PauseButton;
import com.wmj.actors.menu.StartButton;
import com.wmj.enums.GameState;
import com.wmj.utils.BodyUtils;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;
import com.wmj.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Runner runner;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
//    private Box2DDebugRenderer renderer;

    private Rectangle screenTopSide;
    private Rectangle screenBottomSide;

    private PauseButton pauseButton;
    private StartButton startButton;

    private Vector3 touchPoint;

    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setUpWorld();
        setUpCamera();
        setUpFixedMenu();
        setUpMainMenu();
        setUpTouchControlAreas();
        Gdx.input.setInputProcessor(this);
        onGameOver();
//        renderer = new Box2DDebugRenderer();
    }

    private void setUpFixedMenu() {
        //setUpPause();
    }

    private void setUpPause() {
        Rectangle pauseButtonBounds = new Rectangle(
                20,
                getCamera().viewportHeight - Constants.BUTTON_HEIGHT - 20,
                Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
        pauseButton = new PauseButton(pauseButtonBounds, new GamePauseButtonListener());
        addActor(pauseButton);
    }

    private void setUpMainMenu() {
        setUpStart();
    }

    private void setUpStart() {
        Rectangle startButtonBounds = new Rectangle(
                (getCamera().viewportWidth - Constants.BIG_BUTTON_WIDTH) / 2,
                (getCamera().viewportHeight - Constants.BIG_BUTTON_HEIGHT) / 2,
                Constants.BIG_BUTTON_WIDTH,
                Constants.BIG_BUTTON_HEIGHT);
        startButton = new StartButton(startButtonBounds, new GameStartButtonListener());
        addActor(startButton);
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
    }

    private void setUpBackground() {
        addActor(new Background());
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpCharacters() {
        setUpRunner();
        createEnemy();
    }

    private void setUpRunner() {
        if (runner != null) {
            runner.remove();
        }

        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpTouchControlAreas() {
        touchPoint = new Vector3();
        screenTopSide = new Rectangle(
                0,
                getCamera().viewportHeight / 2,
                getCamera().viewportWidth,
                getCamera().viewportHeight / 2);
        screenBottomSide = new Rectangle(
                0,
                0,
                getCamera().viewportWidth,
                getCamera().viewportHeight / 2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameStateManager.getInstance().getGameState() == GameState.PAUSED) {
            return;
        }

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation

    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                createEnemy();
            }
            world.destroyBody(body);
        }
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        // If a menu control was touched ignore the rest
        if (menuControlTouched(touchPoint.x, touchPoint.y)) {
            return super.touchDown(x, y, pointer, button);
        }

        if (GameStateManager.getInstance().getGameState() != GameState.RUNNING) {
            return super.touchDown(x, y, pointer, button);
        }

        if (topSideTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
        } else if (bottomSideTouched(touchPoint.x, touchPoint.y)) {
            runner.slide();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (GameStateManager.getInstance().getGameState() != GameState.RUNNING) {
            return super.touchUp(x, y, pointer, button);
        }

        if (runner.isSliding()) {
            runner.stopSlide();
        }

        return super.touchUp(x, y, pointer, button);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.SPACE) {
            switch (GameStateManager.getInstance().getGameState()) {
                case OVER:
                    onGameStarted();
                    break;
                case RUNNING:
                    onGamePaused();
                    break;
                case PAUSED:
                    onGameResumed();
                    break;
            }
        }

        if (GameStateManager.getInstance().getGameState() != GameState.RUNNING) {
            return super.keyDown(keyCode);
        }

        if (keyCode == Input.Keys.UP) {
            runner.jump();
        } else if (keyCode == Input.Keys.DOWN) {
            runner.slide();
        }

        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (GameStateManager.getInstance().getGameState() != GameState.RUNNING) {
            return super.keyUp(keyCode);
        }

        if (keyCode == Input.Keys.DOWN) {
            if (runner.isSliding()) {
                runner.stopSlide();
            }
        }

        return super.keyUp(keyCode);
    }

    private boolean menuControlTouched(float x, float y) {
        boolean touched = false;

        switch (GameStateManager.getInstance().getGameState()) {
            case OVER:
                touched = startButton.getBounds().contains(x, y);
                break;
            case RUNNING:
            case PAUSED:
                touched = pauseButton.getBounds().contains(x, y);
                break;
        }

        return touched;
    }

    private boolean topSideTouched(float x, float y) {
        return screenTopSide.contains(x, y);
    }

    private boolean bottomSideTouched(float x, float y) {
        return screenBottomSide.contains(x, y);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            if (runner.isHit()) {
                return;
            }
            runner.hit();
            onGameOver();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private class GamePauseButtonListener implements PauseButton.PauseButtonListener {

        @Override
        public void onPause() {
            onGamePaused();
        }

        @Override
        public void onResume() {
            onGameResumed();
        }

    }

    private class GameStartButtonListener implements StartButton.StartButtonListener {

        @Override
        public void onStart() {
            onGameStarted();
        }

    }

    private void onGameStarted() {
        setUpCharacters();
        setUpPause();
        onGameResumed();
    }

    private void onGamePaused() {
        GameStateManager.getInstance().setGameState(GameState.PAUSED);
    }

    private void onGameResumed() {
        GameStateManager.getInstance().setGameState(GameState.RUNNING);
    }

    private void onGameOver() {
        GameStateManager.getInstance().setGameState(GameState.OVER);
        GameStateManager.getInstance().increaseRepetitions();
        setUpMainMenu();
    }

}
