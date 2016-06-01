package com.wmj.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.wmj.enums.GameState;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;

public class StartButton extends GameButton {

    public interface StartButtonListener {
        void onStart();
    }

    private StartButtonListener listener;

    public StartButton(Rectangle bounds, StartButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return GameStateManager.getInstance().isFirstGame()
                ? Constants.BIG_PLAY_REGION_NAME
                : Constants.BIG_RESTART_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameStateManager.getInstance().getGameState() != GameState.OVER) {
            remove();
        }
    }

    @Override
    public void touched() {
        listener.onStart();
    }

}
