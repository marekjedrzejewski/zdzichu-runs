package com.wmj.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.wmj.enums.GameState;
import com.wmj.stages.GameStage;
import com.wmj.utils.Constants;

public class PauseButton extends GameButton implements GameStage.GameListener {

    public interface PauseButtonListener {
        void onPause();

        void onResume();
    }

    private PauseButtonListener listener;
    private GameState gameState;

    public PauseButton(Rectangle bounds, PauseButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return gameState == GameState.PAUSED ? Constants.PLAY_REGION_NAME : Constants.PAUSE_REGION_NAME;
    }

    @Override
    public void touched() {
        if (gameState == GameState.PAUSED) {
            listener.onResume();
        } else {
            listener.onPause();
        }
    }

    @Override
    public void onGameStateChange(GameState newState) {
        if (newState == GameState.OVER) {
            remove();
        }
        gameState = newState;
    }

}
