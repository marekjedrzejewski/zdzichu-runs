package com.wmj.utils;

import com.badlogic.gdx.math.Vector2;

public interface Constants {

    int APP_WIDTH = 800;
    int APP_HEIGHT = 480;
    float WORLD_TO_SCREEN = 32;

    Vector2 WORLD_GRAVITY = new Vector2(0, -8);

    float GROUND_X = 0;
    float GROUND_Y = 0;
    float GROUND_WIDTH = 50f;
    float GROUND_HEIGHT = 1.4f;
    float GROUND_DENSITY = 0f;

    float RUNNER_X = 2;
    float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    float RUNNER_WIDTH = 1f;
    float RUNNER_HEIGHT = 2f;
    float RUNNER_GRAVITY_SCALE = 3f;
    float RUNNER_DENSITY = 0.5f;
    float RUNNER_SLIDE_X = 2f;
    float RUNNER_SLIDE_Y = 1.5f;
    Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);
    float RUNNER_HIT_ANGULAR_IMPULSE = 50f;

    float ENEMY_X = 25f;
    float ENEMY_DENSITY = RUNNER_DENSITY;
    float RUNNING_SHORT_ENEMY_Y = 1.5f;
    float RUNNING_LONG_ENEMY_Y = 2f;
    float FLYING_ENEMY_Y = 3f;
    Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-10f, 0);

    String BACKGROUND_IMAGE_PATH = "backdrops/notebook_800x480.jpg";
    String SPLASH_SCREEN_IMAGE_PATH = "interface_elements/splash.png";
    String INTERFACE_ELEMENTS_ATLAS_PATH = "interface_elements/buttons.atlas";

    int BIG_BUTTON_HEIGHT = 200;
    int BIG_BUTTON_WIDTH = 200;
    int BUTTON_HEIGHT = 60;
    int BUTTON_WIDTH = 60;

    String BIG_PLAY_REGION_NAME = "play200";
    String BIG_RESTART_REGION_NAME = "restart200";
    String PAUSE_REGION_NAME = "pause60";
    String PLAY_REGION_NAME = "play60";

    String SOUND_ON_REGION_NAME = "sound_on";
    String SOUND_OFF_REGION_NAME = "sound_off";
    String MUSIC_ON_REGION_NAME = "music_on";
    String MUSIC_OFF_REGION_NAME = "music_off";

    String GAME_MUSIC = "sounds/soundtrack_loop.ogg";

    AnimParameters JASTRZAB_ANIM = new AnimParameters("falcon_sheet_64.png",
            30, 64, 64);
    AnimParameters MALUCH_ANIM = new AnimParameters("car_sheet_96.png",
            15, 96, 128);

}
