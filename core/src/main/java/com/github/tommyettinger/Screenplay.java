package com.github.tommyettinger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
        String all = Gdx.files.internal(scriptFileName).readString("UTF-8");
        texts = TextTools.split(all.stripTrailing(), "\n--\n");
    }

    public int advance(TypingLabel label){
        return advance(label, 1);
    }

    public int advance(TypingLabel label, int move){
        if((index += move) >= texts.length || index < 0) index = 0;
        label.restart(texts[index]);
        label.invalidateHierarchy();
        return index;
    }
}
