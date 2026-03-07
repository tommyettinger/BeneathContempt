package com.github.tommyettinger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.github.tommyettinger.ds.IntObjectMap;
import com.github.tommyettinger.random.Xoshiro160RoadroxoRandom;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.TypingLabel;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public SpriteBatch batch;

    // random number generator; this one is more efficient on GWT, but less-so on desktop.
    public Xoshiro160RoadroxoRandom rng;

    public long seed;

    public ScreenViewport uiViewport;

    public Stage stage;

    public Screenplay screenplay;

    public TypingLabel makeLabel(String text){
        final TypingLabel label = new TypingLabel(text, Assets.getMonogramFamily());
        label.setDefaultToken("[#]{EASE=-0.5;0.25}");
        label.setWrap(true);
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(label.hasEnded()) {
                    screenplay.advance(label);
                }
                else
                    label.skipToTheEnd();
            }
        });
        return label;
    }

    @Override
    public void create() {
        rng = new Xoshiro160RoadroxoRandom(123);
        batch = new SpriteBatch();
        uiViewport = new ScreenViewport();
        stage = new Stage(uiViewport, batch);
        screenplay = new Screenplay(this, "text/intro.txt");
        TypingLabel label = makeLabel(screenplay.texts[screenplay.index]);
        label.setAlignment(Align.center);
        Table table = new Table();
        table.setFillParent(true);
        table.add(label).center().size(630, 480);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.75f, 0.55f, 0.3f, 1f);
        uiViewport.apply(false);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        uiViewport.update(width, height, true);
    }
}
