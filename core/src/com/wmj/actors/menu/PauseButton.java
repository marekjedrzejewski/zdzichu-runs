package com.wmj.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.wmj.enums.GameState;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;

public class PauseButton extends GameButton {

    public interface PauseButtonListener {
        void onPause();
        void onResume();
    }

    private PauseButtonListener listener;

    public PauseButton(Rectangle bounds, PauseButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return GameStateManager.getInstance().getGameState() == GameState.PAUSED
                ? Constants.PLAY_REGION_NAME
                : Constants.PAUSE_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameStateManager.getInstance().getGameState() == GameState.OVER) {
            remove();
        }
    }

    @Override
    public void touched() {
        if (GameStateManager.getInstance().getGameState() == GameState.PAUSED) {
            listener.onResume();
        } else {
            listener.onPause();
        }
    }

}
