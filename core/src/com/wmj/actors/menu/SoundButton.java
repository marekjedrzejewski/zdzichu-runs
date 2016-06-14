package com.wmj.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.wmj.utils.AudioUtils;

public class SoundButton extends GameButton {

    public SoundButton(Rectangle bounds) {
        super(bounds);
    }

    @Override
    protected String getRegionName() {
        return AudioUtils.getInstance().getSoundRegionName();
    }

    @Override
    public void touched() {
        AudioUtils.getInstance().toggleMusic();
        AudioUtils.getInstance().toggleSound();
    }

}
