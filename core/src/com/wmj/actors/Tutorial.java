package com.wmj.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.wmj.actors.menu.PauseButton;
import com.wmj.enums.GameState;
import com.wmj.utils.Constants;
import com.wmj.utils.GameStateManager;

public class Tutorial extends Actor {

    private TextureRegion textureRegion;
    private Rectangle bounds;
    private TextureAtlas textureAtlas;
    private PauseButton.PauseButtonListener listener;

    public Tutorial(Rectangle bounds, PauseButton.PauseButtonListener listener) {
        this.bounds = bounds;
        this.listener = listener;

        textureAtlas = new TextureAtlas(Constants.INTERFACE_ELEMENTS_ATLAS_PATH);
        textureRegion = textureAtlas.findRegion(Constants.TUTORIAL_REGION_NAME);

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.delay(4f));
        sequenceAction.addAction(Actions.removeActor());
        addAction(sequenceAction);

        setWidth(bounds.width);
        setHeight(bounds.height);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touched();
                remove();
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameStateManager.getInstance().getGameState() == GameState.RUNNING) {
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void touched() {
        if (GameStateManager.getInstance().getGameState() == GameState.PAUSED) {
            listener.onResume();
        } else {
            listener.onPause();
        }
    }

}
