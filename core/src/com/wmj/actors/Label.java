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

public class Label extends Actor {

    private Rectangle bounds;
    private BitmapFont font;
    private String text;

    public Label(Rectangle bounds) {
        this.bounds = bounds;
        this.text = Constants.GAME_NAME;
        setWidth(bounds.width);
        setHeight(bounds.height);
        loadFont();
    }

    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        font = generator.generateFont(parameter);
        font.setColor(.21f, .22f, .21f, 1f);
        generator.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameStateManager.getInstance().getGameState() == GameState.RUNNING
                || GameStateManager.getInstance().getGameState() == GameState.PAUSED) {
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, text, bounds.x, bounds.y, bounds.width, Align.center, true);
    }

    public void setNormalText() {
        text = Constants.GAME_NAME;
    }

    public void setRecordText() {
        text = Constants.RECORD_TEXT;
    }

}
