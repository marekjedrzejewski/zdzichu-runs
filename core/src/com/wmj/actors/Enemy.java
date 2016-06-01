package com.wmj.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.wmj.box2d.EnemyUserData;
import com.wmj.enums.GameState;
import com.wmj.utils.AnimParameters;
import com.wmj.utils.GameStateManager;

public class Enemy extends GameActor {

    private static final float TIME_PER_FRAME = 0.033f;

    Animation enemyAnim;
    TextureRegion currentFrame;

    float stateTime;

    public Enemy(Body body) {
        super(body);

        EnemyUserData enemyData = (EnemyUserData) body.getUserData();
        AnimParameters animParameters= enemyData.getAnimParameters();
        enemyAnim = loadAnimation(animParameters.getPath(), animParameters.getFrames(),
                animParameters.getFrames_w(), animParameters.getFrames_h());
        stateTime = 0f;
    }

    private Animation loadAnimation(String filename, int frames, int frame_w, int frame_h) {
        Texture animSheet = new Texture(Gdx.files.internal("animation_sheets/" + filename));
        TextureRegion[] runFrames = new TextureRegion[frames];
        for (int i = 0; i < frames ; i++) {
            runFrames[i] = new TextureRegion(animSheet, i*frame_w, 0, frame_w, frame_h);
        }
        return new Animation(TIME_PER_FRAME, runFrames);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x = screenRectangle.x - (screenRectangle.width * 0.1f);
        float y = screenRectangle.y;

        if (GameStateManager.getInstance().getGameState() != GameState.PAUSED) {
            stateTime += Gdx.graphics.getDeltaTime();
        }

        currentFrame = enemyAnim.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, x, y);
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

}
