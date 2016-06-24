package com.wmj.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.wmj.enums.GameState;

public class GameStateManager {

    private static GameStateManager instance = new GameStateManager();

    private GameState gameState;

    private static int gameRepetitions;
    private static float gameScore;

    private static final String PREFERENCES_NAME = "preferences";
    private static final String SCORE_PREFERENCE = "score";

    public static GameStateManager getInstance() {
        return instance;
    }

    private GameStateManager() {
        gameState = GameState.RUNNING;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isFirstGame() {
        return gameRepetitions == 1;
    }

    public void increaseRepetitions() {
        gameRepetitions++;
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFERENCES_NAME);
    }

    private void saveInteger(String key, int value) {
        Preferences preferences = getPreferences();
        preferences.putInteger(key, value);
        preferences.flush();
    }

    public float getScore() {
        return gameScore;
    }

    public void setScore(float score) {
        gameScore = score;
    }

    public void updateScore(float delta) {
        gameScore += delta;
    }

    public int getMaxScore() {
        return getPreferences().getInteger(SCORE_PREFERENCE, 0);
    }

    public void setMaxScore(int score) {
        saveInteger(SCORE_PREFERENCE, score);
    }

    public boolean isNewMaxScore() {
        return getScore() > getMaxScore();
    }

    public void updateNewMaxScore() {
        setMaxScore((int) Math.floor(gameScore));
    }

}
