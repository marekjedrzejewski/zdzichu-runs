package com.wmj.utils;

/**
 * Created by lwsol on 25.05.2016.
 */
public class AnimParameters {
    private String path;
    private int frames;
    private int frames_h;
    private int frames_w;

    public AnimParameters(String path, int frames, int frames_h, int frames_w) {
        this.path = path;
        this.frames = frames;
        this.frames_h = frames_h;
        this.frames_w = frames_w;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public int getFrames_h() {
        return frames_h;
    }

    public void setFrames_h(int frames_h) {
        this.frames_h = frames_h;
    }

    public int getFrames_w() {
        return frames_w;
    }

    public void setFrames_w(int frames_w) {
        this.frames_w = frames_w;
    }
}
