package com.wmj.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.wmj.box2d.EnemyUserData;

public class Enemy extends GameActor {

    private static final int FRAMES = 15;
    private static final int FRAME_W = 212;
    private static final int FRAME_H = 96;



    Animation carAnimation;
    Texture carSheet;
    TextureRegion[] carFrames;
    SpriteBatch spriteBatch;
    TextureRegion currentFrame;

    float stateTime;

    public Enemy(Body body) {
        super(body);
        carSheet = new Texture(Gdx.files.internal("animation_sheets/ground.png"));


//        carFrames = new TextureRegion[FRAMES];
//        for (int i = 0, row = 0; i < FRAMES; i++) {
//            //ToDo:for two lines
//            if(i*FRAME_W > carSheet.getWidth()){
//                row++;
//            }
//            carFrames[i] = new TextureRegion(carSheet, i*FRAME_W, row, FRAME_W, FRAME_H);
//        }
//        carAnimation = new Animation(0.033f, carFrames);
//        stateTime = 0f;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
//        stateTime += Gdx.graphics.getDeltaTime();           // #15
//        currentFrame = carAnimation.getKeyFrame(stateTime, true);  // #16
        batch.draw(carSheet, 50, 50);
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
