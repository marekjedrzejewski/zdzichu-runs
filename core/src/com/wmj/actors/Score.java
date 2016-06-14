package com.wmj.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.wmj.enums.GameState;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;

public class Score extends Actor {

    private float score;
    private int multiplier;
    private Rectangle bounds;
    private BitmapFont font;

    public Score(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        score = 0;
        multiplier = 5;
        loadFont();
    }

    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        font = generator.generateFont(parameter);
        font.setColor(.21f, .22f, .21f, 1f);
        generator.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameStateManager.getInstance().getGameState() != GameState.RUNNING) {
            return;
        }

        score += multiplier * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, String.valueOf(getScore()), bounds.x, bounds.y, bounds.width, Align.topRight, true);
    }

    public int getScore() {
        return (int) Math.floor(score);
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void reset() {
        score = 0;
    }

}