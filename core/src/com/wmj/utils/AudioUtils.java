package com.wmj.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtils {

    private static AudioUtils instance = new AudioUtils();
    private static Music music;

    private static final String PREFERENCES_NAME = "preferences";
    private static final String MUSIC_ON_PREFERENCE = "music_on";
    private static final String SOUND_ON_PREFERENCE = "sound_on";

    private AudioUtils() {
    }

    public static AudioUtils getInstance() {
        return instance;
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFERENCES_NAME);
    }

    public void createMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAME_MUSIC));
        music.setLooping(true);
        playMusic();
    }

    public void playMusic() {
        boolean musicOn = getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true);
        if (musicOn) {
            music.play();
        }
    }

    public void pauseMusic() {
        music.pause();
    }

    public void stopMusic() {
        music.stop();
    }

    public void toggleMusic() {
        saveBoolean(MUSIC_ON_PREFERENCE, !getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true));
    }

    public void disposeMusic() {
        music.dispose();
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

    public void toggleSound() {
        saveBoolean(SOUND_ON_PREFERENCE, !getPreferences().getBoolean(SOUND_ON_PREFERENCE, true));
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

    public String getMusicRegionName() {
        boolean musicOn = getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true);
        return musicOn ? Constants.MUSIC_ON_REGION_NAME : Constants.MUSIC_OFF_REGION_NAME;
    }

}
