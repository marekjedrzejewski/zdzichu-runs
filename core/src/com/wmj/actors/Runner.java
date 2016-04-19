package com.wmj.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.wmj.box2d.RunnerUserData;

public class Runner extends GameActor {

    private boolean jumping;
    private boolean dodging;
    private boolean hit;

    private static final int FRAMES = 25;
    private static final int FRAME_W = 106;
    private static final int FRAME_H = 128;

    Animation runAnim;
    Texture runSheet;
    TextureRegion[] runFrames;
    TextureRegion currentFrame;
    float stateTime;

    public Runner(Body body) {
        super(body);

        runSheet = new Texture(Gdx.files.internal("animation_sheets/run_sheet_128.png"));
        runFrames = new TextureRegion[FRAMES];
        for (int i = 0; i < FRAMES; i++) {
            runFrames[i] = new TextureRegion(runSheet, i*FRAME_W, 0, FRAME_W, FRAME_H);
        }
        runAnim = new Animation(0.033f, runFrames);
        stateTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x = screenRectangle.x - (screenRectangle.width * 0.1f);
        float y = screenRectangle.y;

        if(jumping){
            batch.draw(currentFrame, x, y);
        }else{
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = runAnim.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, x, y);
        }
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump() {
        if (!(jumping || dodging || hit)) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void dodge() {
        if (!(jumping || hit)) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;

        if (!hit) {
            body.setTransform(getUserData().getRunningPosition(), 0f);
        }     }

    public boolean isDodging() {
        return dodging;
    }

    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }

}
