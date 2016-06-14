package com.wmj.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.wmj.enums.GameState;

public class AudioUtils {

    private static AudioUtils instance = new AudioUtils();
    private static Sound music = createSound(Constants.GAME_MUSIC);

    private static final String PREFERENCES_NAME = "preferences";
    private static final String SOUND_ON_PREFERENCE = "sound_on";

    private AudioUtils() {
    }

    public static AudioUtils getInstance() {
        return instance;
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFERENCES_NAME);
    }

    public void playMusic() {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);

        if (GameStateManager.getInstance().getGameState() != GameState.RUNNING) {
            return;
        }

        if (soundOn) {
            music.loop();
        }
    }

    public void stopMusic() {
        music.stop();
    }

    public void toggleMusic() {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);

        if (soundOn) {
            saveBoolean(SOUND_ON_PREFERENCE, false);
            stopMusic();
        } else {
            saveBoolean(SOUND_ON_PREFERENCE, true);
            playMusic();
        }
    }

    public static Sound createSound(String soundFileName) {
        return Gdx.audio.newSound(Gdx.files.internal(soundFileName));
    }

    public void playSound(Sound sound) {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);
        if (soundOn) {
            sound.play();
        }
    }

    private void saveBoolean(String key, boolean value) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(key, value);
        preferences.flush();
    }

    public String getSoundRegionName() {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);
        return soundOn ? Constants.SOUND_ON_REGION_NAME : Constants.SOUND_OFF_REGION_NAME;
    }

}
