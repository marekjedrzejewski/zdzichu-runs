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
import com.wmj.enums.GameState;

public class Runner extends GameActor {

    private boolean jumping;
    private boolean dodging;
    private boolean hit;

    private static final float TIME_PER_FRAME = 0.033f;

    private static final int RUN_FRAMES = 25;
    private static final int RUN_FRAME_W = 106;
    private static final int RUN_FRAME_H = 128;

    private static final int JUMP_FRAMES = 31;
    private static final int JUMP_FRAME_W = 66;
    private static final int JUMP_FRAME_H = 128;

    private static final int SLIDE_FRAMES = 15;
    private static final int SLIDE_FRAME_W = 128;
    private static final int SLIDE_FRAME_H = 32;

    Animation runAnim, jumpAnim, slideAnim;
    TextureRegion currentFrame;
    float stateTime;

    public Runner(Body body) {
        super(body);

        runAnim = loadAnimation("run_sheet_128.png", RUN_FRAMES, RUN_FRAME_W, RUN_FRAME_H);
        jumpAnim = loadAnimation("jump_sheet_128.png", JUMP_FRAMES, JUMP_FRAME_W, JUMP_FRAME_H);
        slideAnim = loadAnimation("slide_sheet_32.png", SLIDE_FRAMES, SLIDE_FRAME_W, SLIDE_FRAME_H);

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

        stateTime += Gdx.graphics.getDeltaTime();

        if(jumping){
            currentFrame = jumpAnim.getKeyFrame(stateTime, false);
            batch.draw(currentFrame, x, y);
        } else if (dodging) {
            currentFrame = slideAnim.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, x, y);
        } else {
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
            stateTime = 0;
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void slide() {
        if (!(jumping || hit)) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopSlide() {
        dodging = false;

        if (!hit) {
            body.setTransform(getUserData().getRunningPosition(), 0f);
        }
    }

    public boolean isSliding() {
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
