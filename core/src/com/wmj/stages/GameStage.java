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
import com.wmj.actors.Label;
import com.wmj.actors.Runner;
import com.wmj.actors.Score;
import com.wmj.actors.Tutorial;
import com.wmj.actors.menu.AboutButton;
import com.wmj.actors.menu.AboutLabel;
import com.wmj.actors.menu.PauseButton;
import com.wmj.actors.menu.SoundButton;
import com.wmj.actors.menu.StartButton;
import com.wmj.enums.GameState;
import com.wmj.utils.AudioUtils;
import com.wmj.utils.BodyUtils;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;
import com.wmj.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private int createdEnemies = 1;
    private int counter;
    private int speed = 300;

    private World world;
    private Ground ground;
    private Runner runner;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;

    private Rectangle jumpGestureArea;
    private Rectangle slideGestureArea;

    private AboutButton aboutButton;
    private PauseButton pauseButton;
    private SoundButton soundButton;
    private StartButton startButton;

    private Score score;

    private Vector3 touchPoint;

    public GameStage(World world) {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));

        this.world = world;

        setUpCamera();
        setUpStageBase();
        setUpMainMenu();
        setUpTouchControlAreas();
        Gdx.input.setInputProcessor(this);
        onGameOver();
    }

    private void setUpStageBase() {
        setUpWorld();
        setUpFixedMenu();
    }

    private void setUpFixedMenu() {
        setUpSound();
        setUpScore();
    }

    private void setUpMainMenu() {
        setUpLabel();
        setUpStart();
        setUpAbout();
    }

    private void setUpLabel() {
        Rectangle gameLabelBounds = new Rectangle(
                0,
                getCamera().viewportHeight - 2 * Constants.SPACE_HEIGHT,
                getCamera().viewportWidth,
                getCamera().viewportHeight / 4);
        addActor(new Label(gameLabelBounds));
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

    private void setUpAbout() {
        Rectangle aboutButtonBounds = new Rectangle(
                getCamera().viewportWidth - Constants.BUTTON_WIDTH - Constants.SPACE_WIDTH,
                getCamera().viewportHeight - 2 * Constants.BUTTON_HEIGHT - 2 * Constants.SPACE_HEIGHT,
                Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
        aboutButton = new AboutButton(aboutButtonBounds, new GameAboutButtonListener());
        addActor(aboutButton);
    }

    private void setUpAboutText() {
        Rectangle gameLabelBounds = new Rectangle(
                0,
                getCamera().viewportHeight * 3 / 4 - Constants.SPACE_WIDTH,
                getCamera().viewportWidth,
                getCamera().viewportHeight / 2);
        addActor(new AboutLabel(gameLabelBounds));
    }

    private void setUpPause() {
        Rectangle pauseButtonBounds = new Rectangle(
                Constants.SPACE_WIDTH,
                getCamera().viewportHeight - Constants.BUTTON_HEIGHT - Constants.SPACE_HEIGHT,
                Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
        pauseButton = new PauseButton(pauseButtonBounds, new GamePauseButtonListener());
        addActor(pauseButton);
    }

    private void setUpSound() {
        Rectangle soundButtonBounds = new Rectangle(
                Constants.SPACE_WIDTH,
                getCamera().viewportHeight - 2 * Constants.BUTTON_HEIGHT - 2 * Constants.SPACE_HEIGHT,
                Constants.BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
        soundButton = new SoundButton(soundButtonBounds);
        addActor(soundButton);
    }

    private void setUpScore() {
        Rectangle scoreBounds = new Rectangle(
                getCamera().viewportWidth - Constants.BIG_BUTTON_WIDTH - Constants.SPACE_WIDTH,
                getCamera().viewportHeight - 2 * Constants.SPACE_HEIGHT,
                Constants.BIG_BUTTON_WIDTH,
                Constants.BUTTON_HEIGHT);
        score = new Score(scoreBounds);
        addActor(score);
    }

    private void setUpWorld() {
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

    private void setUpTutorial() {
        Rectangle tutorialBounds = new Rectangle(
                0,
                0,
                getCamera().viewportWidth,
                getCamera().viewportHeight);
        addActor(new Tutorial(tutorialBounds, new GamePauseButtonListener()));
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpTouchControlAreas() {
        touchPoint = new Vector3();
        jumpGestureArea = new Rectangle(
                0,
                getCamera().viewportHeight / 2,
                getCamera().viewportWidth,
                getCamera().viewportHeight / 2);
        slideGestureArea = new Rectangle(
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
    }

    private void update(Body body) {
//        int a = createdBodies;
//        if(createdBodies > createdEnemies) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                createEnemy();
            }
            world.destroyBody(body);
        }
//        }
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

        if (jumpGestureAreaTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
        } else if (slideGestureAreaTouched(touchPoint.x, touchPoint.y)) {
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

        if (soundButton.getBounds().contains(x, y)) {
            touched = true;
        }

        if (aboutButton.getBounds().contains(x, y)
                && (GameStateManager.getInstance().getGameState() == GameState.ABOUT
                || GameStateManager.getInstance().getGameState() == GameState.OVER)) {
            touched = true;
        }

        return touched;
    }

    private boolean jumpGestureAreaTouched(float x, float y) {
        return jumpGestureArea.contains(x, y);
    }

    private boolean slideGestureAreaTouched(float x, float y) {
        return slideGestureArea.contains(x, y);
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

    private class GameAboutButtonListener implements AboutButton.AboutButtonListener {

        @Override
        public void onAbout() {
            if (GameStateManager.getInstance().getGameState() == GameState.OVER) {
                onGameAbout();
            } else {
                clear();
                setUpStageBase();
                setUpLabel();
                onGameOver();
            }
        }

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

    private void onGameAbout() {
        GameStateManager.getInstance().setGameState(GameState.ABOUT);
        clear();
        setUpStageBase();
        setUpLabel();
        setUpAboutText();
        setUpAbout();
    }

    private void onGameStarted() {
        setUpCharacters();
        setUpPause();
        score.reset();
        onGameResumed();
    }

    private void onGamePaused() {
        GameStateManager.getInstance().setGameState(GameState.PAUSED);
        AudioUtils.getInstance().stopMusic();
        pauseButton.refreshStyle();
        setUpTutorial();
    }

    private void onGameResumed() {
        GameStateManager.getInstance().setGameState(GameState.RUNNING);
        AudioUtils.getInstance().playMusic();
        pauseButton.refreshStyle();
    }

    private void onGameOver() {
        GameStateManager.getInstance().setGameState(GameState.OVER);
        GameStateManager.getInstance().increaseRepetitions();
        AudioUtils.getInstance().stopMusic();
        setUpMainMenu();
    }
}
