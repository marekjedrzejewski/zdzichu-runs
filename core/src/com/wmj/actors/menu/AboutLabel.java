package com.wmj.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.wmj.utils.Constants;

public class AboutLabel extends Actor {

    private Rectangle bounds;
    private BitmapFont font;

    public AboutLabel(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        loadFont();
    }

    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);
        font.setColor(.21f, .22f, .21f, 1f);
        generator.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, Constants.ABOUT_TEXT, bounds.x, bounds.y, bounds.width, Align.center, true);
    }

}
