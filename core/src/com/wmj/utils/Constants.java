package com.wmj.utils;

import com.badlogic.gdx.math.Vector2;

public interface Constants {

    String GAME_NAME = "Zdzichu Runs";
    String RECORD_TEXT = "New Record!";
    String ABOUT_TEXT = "Authors:\nMarek Jedrzejewski\nPawel Moniewski\nLukasz Wsol\n\nPowered by LibGDX";

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

    String BACKGROUND_IMAGE_PATH = "backdrops/notebook_800x480.jpg";
    String SPLASH_SCREEN_IMAGE_PATH = "interface_elements/splash.png";
    String INTERFACE_ELEMENTS_ATLAS_PATH = "interface_elements/buttons.pack";

    int BIG_BUTTON_HEIGHT = 200;
    int BIG_BUTTON_WIDTH = 200;
    int BUTTON_HEIGHT = 60;
    int BUTTON_WIDTH = 60;
    int SPACE_HEIGHT = 20;
    int SPACE_WIDTH = 20;

    String ABOUT_REGION_NAME = "about";
    String BIG_PLAY_REGION_NAME = "play200";
    String BIG_RESTART_REGION_NAME = "restart200";
    String PAUSE_REGION_NAME = "pause60";
    String PLAY_REGION_NAME = "play60";
    String TUTORIAL_REGION_NAME = "tutorial";

    String SOUND_ON_REGION_NAME = "sound_on";
    String SOUND_OFF_REGION_NAME = "sound_off";

    String GAME_MUSIC = "sounds/soundtrack_loop.ogg";
    String RUNNER_JUMP_SOUND = "sounds/jump.wav";
    String RUNNER_HIT_SOUND = "sounds/hit.wav";

    String FONT_NAME = "lato_bold.ttf";

    //ENEMY STUFF
    int INCREASE_COUNTER = 5;
    float ENEMY_X = 25f;
    float ENEMY_DENSITY = RUNNER_DENSITY;
    float ENEMY_Y = 1.2f;
    float ENEMY_Y_BIG = 1.6f;
    float FLYING_ENEMY_Y = 3.4f;
    Vector2 STATIC_ENEMY_LINEAR_VELOCITY = new Vector2(-9.3f, 0);
    Vector2 DYNAMIC_ENEMY_LINEAR_VELOCITY = new Vector2(-14f, 0);
    Vector2 SUPER_DYNAMIC_ENEMY_LINEAR_VELOCITY = new Vector2(-20f, 0);

    AnimParameters JASTRZAB_ANIM = new AnimParameters("animation_sheets/falcon_sheet_64.png",
            30, 64, 64);
    AnimParameters MALUCH_ANIM = new AnimParameters("animation_sheets/car_sheet_96.png",
            15, 96, 128);
    AnimParameters CACTUS_ANIM_1 = new AnimParameters("static_actors/cactus_small1.png",
            1, 64, 64);
    AnimParameters CACTUS_ANIM_2 = new AnimParameters("static_actors/cactus_small2.png",
            1, 64, 64);
    AnimParameters CACTUS_ANIM_3 = new AnimParameters("static_actors/cactus_tall3.png",
            1, 128, 64);
    AnimParameters GARBAGE_ANIM_1 = new AnimParameters("static_actors/garbage_container_blue.png",
            1, 96, 107);
    AnimParameters GARBAGE_ANIM_2 = new AnimParameters("static_actors/garbage_container_green.png",
            1, 96, 107);
    AnimParameters GARBAGE_ANIM_3 = new AnimParameters("static_actors/garbage_container_pink.png",
            1, 96, 107);
    AnimParameters GARBAGE_ANIM_4 = new AnimParameters("static_actors/garbage_container_red.png",
            1, 96, 107);
    AnimParameters GARBAGE_ANIM_5 = new AnimParameters("static_actors/garbage_container_yellow.png",
            1, 96, 107);

}
