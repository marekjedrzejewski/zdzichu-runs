package com.wmj;

import com.badlogic.gdx.Game;
import com.wmj.screens.GameScreen;
import com.wmj.screens.SplashScreen;

public class ZdzichuRuns extends Game {

    @Override
    public void create () {
        setScreen(new SplashScreen(this));
    }

}
