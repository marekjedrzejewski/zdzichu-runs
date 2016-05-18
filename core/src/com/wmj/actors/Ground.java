package com.wmj.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.wmj.box2d.GroundUserData;
import com.wmj.enums.GameState;

public class Ground extends GameActor {

    public Ground(Body body) {
        super(body);
    }

    @Override
    public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (gameState == GameState.PAUSED) {
            return;
        }
    }

}
