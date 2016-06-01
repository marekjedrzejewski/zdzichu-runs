package com.wmj.utils;

import com.wmj.enums.GameState;

public class GameStateManager {

    private static GameStateManager instance = new GameStateManager();

    private GameState gameState;

    private static int gameRepetitions;

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

}
