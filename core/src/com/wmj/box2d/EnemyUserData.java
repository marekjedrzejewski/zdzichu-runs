package com.wmj.box2d;

import com.badlogic.gdx.math.Vector2;
import com.wmj.enums.UserDataType;
import com.wmj.utils.AnimParameters;
import com.wmj.utils.Constants;

public class EnemyUserData extends UserData {

    private Vector2 linearVelocity;
    private AnimParameters animParameters;

    public EnemyUserData(float width, float height, AnimParameters animParameters, Vector2 velocity) {
        super(width, height);
        userDataType = UserDataType.ENEMY;
        this.animParameters = animParameters;
        linearVelocity = velocity;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    public AnimParameters getAnimParameters() {
        return animParameters;
    }

}
