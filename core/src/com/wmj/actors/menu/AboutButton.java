package com.wmj.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.wmj.enums.GameState;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;

public class AboutButton extends GameButton {

    public interface AboutButtonListener {
        void onAbout();
    }

    private AboutButtonListener listener;

    public AboutButton(Rectangle bounds, AboutButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameStateManager.getInstance().getGameState() == GameState.RUNNING
                || GameStateManager.getInstance().getGameState() == GameState.PAUSED) {
            remove();
        }
    }

    @Override
    protected String getRegionName() {
        return Constants.PAUSE_REGION_NAME;
        //return Constants.ABOUT_REGION_NAME;
    }

    @Override
    public void touched() {
        listener.onAbout();
    }

}
