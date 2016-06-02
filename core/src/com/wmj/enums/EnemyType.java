package com.wmj.enums;

import com.wmj.utils.Constants;
import com.wmj.utils.AnimParameters;

public enum EnemyType {

//    //Smietnik, Pies, Kaktus
//    RUNNING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY,
//            Constants.RUNNING_SMALL_ENEMY_REGION_NAMES),
//    //Kaktus
//    RUNNING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY,
//            Constants.RUNNING_WIDE_ENEMY_REGION_NAMES),
    CACTUS_3(1f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
        Constants.CACTUS_ANIM_3),
    CACTUS_2(1f, 1f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.CACTUS_ANIM_2),
    CACTUS_1(1f, 1f, Constants.ENEMY_X, Constants.ENEMY_Y, Constants.ENEMY_DENSITY,
            Constants.CACTUS_ANIM_1),
    MALUCH(3f, 2f, Constants.ENEMY_X, Constants.ENEMY_Y_BIG, Constants.ENEMY_DENSITY,
            Constants.MALUCH_ANIM),
    JASTRZAB(1f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY,
            Constants.JASTRZAB_ANIM);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private AnimParameters animParameters;

    EnemyType(float width, float height, float x, float y, float density,
              AnimParameters animParameters) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
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

}
