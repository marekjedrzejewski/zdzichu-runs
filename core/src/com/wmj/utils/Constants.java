package com.wmj.utils;

import com.badlogic.gdx.math.Vector2;

public interface Constants {
    int APP_WIDTH = 800;
    int APP_HEIGHT = 480;

    Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    float GROUND_X = 0;
    float GROUND_Y = 0;
    float GROUND_WIDTH = 50f;
    float GROUND_HEIGHT = 2f;
    float GROUND_DENSITY = 0f;

    float RUNNER_X = 2;
    float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    float RUNNER_WIDITH = 1f;
    float RUNNER_HEIGHT = 2f;
    float RUNNER_DENSITY = 0.5f;
}
