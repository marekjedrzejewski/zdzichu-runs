package com.wmj.enums;

import com.badlogic.gdx.math.Vector2;
import com.wmj.utils.Constants;
import com.wmj.utils.AnimParameters;

public enum EnemyType {

//    //Smietnik, Pies, Kaktus
//    RUNNING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY,
//            Constants.RUNNING_SMALL_ENEMY_REGION_NAMES),
//    //Kaktus
//    RUNNING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY,
//            Constants.RUNNING_WIDE_ENEMY_REGION_NAMES),

    CACTUS_3(1f, 3f, Constants.ENEMY_X, 2f, Constants.ENEMY_DENSITY,
        Constants.CACTUS_ANIM_3, Constants.STATIC_ENEMY_LINEAR_VELOCITY),
    CACTUS_2(1f, 1f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.CACTUS_ANIM_2, new Vector2(-30f, 0)),
    CACTUS_1(1f, 1f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY,
            Constants.CACTUS_ANIM_1, Constants.STATIC_ENEMY_LINEAR_VELOCITY),
    MALUCH_1(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
            Constants.MALUCH_ANIM, Constants.DYNAMIC_ENEMY_LINEAR_VELOCITY),
    MALUCH_2(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
            Constants.MALUCH_ANIM, new Vector2(-20f, 0)),
    JASTRZAB_1(1f, 1.5f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY,
            Constants.JASTRZAB_ANIM, Constants.SUPER_DYNAMIC_ENEMY_LINEAR_VELOCITY),
    JASTRZAB_2(1f, 1.5f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY,
             Constants.JASTRZAB_ANIM, new Vector2(-25f, 0)),
    GARBAGE_1(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
               Constants.GARBAGE_ANIM_1, Constants.STATIC_ENEMY_LINEAR_VELOCITY),
    GARBAGE_2(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
            Constants.GARBAGE_ANIM_2, Constants.STATIC_ENEMY_LINEAR_VELOCITY);
//    GARBAGE_3(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
//            Constants.GARBAGE_ANIM_3, Constants.STATIC_ENEMY_LINEAR_VELOCITY),
//    GARBAGE_4(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
//            Constants.GARBAGE_ANIM_4, Constants.STATIC_ENEMY_LINEAR_VELOCITY),
//    GARBAGE_5(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
//            Constants.GARBAGE_ANIM_5, Constants.STATIC_ENEMY_LINEAR_VELOCITY),

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private AnimParameters animParameters;
    private Vector2 velocity;

    EnemyType(float width, float height, float x, float y, float density, AnimParameters animParameters,
              Vector2 velocity) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.density = density;
        this.animParameters = animParameters;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

    public AnimParameters getAnimParameters() {
        return animParameters;
    }

    public Vector2 getVelocity(){ return velocity; }

}
