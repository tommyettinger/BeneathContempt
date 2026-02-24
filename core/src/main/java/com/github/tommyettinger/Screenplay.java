package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.digital.TextTools;
import com.github.tommyettinger.textra.TypingLabel;

public class Screenplay {
    public Main main;
    public Stage stage;
    public String[] texts;
    public int index = 0;

    public Screenplay(Main main, String scriptFileName){
        this.main = main;
        this.stage = main.stage;
        texts = TextTools.split(Gdx.files.internal(scriptFileName).readString("UTF-8"), "\n--\n");
    }

    public int advance(TypingLabel label){
        if(++index >= texts.length) index = 0;
        label.restart(texts[index]);
        label.invalidateHierarchy();
        return index;
    }
}
