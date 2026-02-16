package com.github.tommyettinger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

    // Stores all images we use here efficiently, as well as the font image
    public TextureAtlas atlas;
    // This maps chars, such as '#', to specific images, such as a pillar.
    public IntObjectMap<TextureAtlas.AtlasRegion> charMapping;

    public Font font;

    public ScreenViewport uiViewport;

    public Stage stage;

    /**
     * Returns a Font configured to use a small variable-width bitmap font with extensive coverage of European scripts,
     * <a href="https://datagoblin.itch.io/monogram">Monogram</a>. Monogram has good coverage of Unicode, including all
     * of Greek, at least most of Cyrillic, and a good amount of extended Latin. This does not scale well except to
     * integer multiples, but it should look very crisp at its default size of about 12 pixels tall with variable width.
     * This should have equivalent metrics to {@link #getMonogramItalic()}.
     * The Texture used for this is an unusual (and unusually small) size, 250x180, so that it is easier to pack into a
     * scene2d.ui Skin's atlas.
     * This defaults to having {@link Font#integerPosition} set to false, which is the usual default.
     * This may work well in a font family with other fonts that do not use a distance field effect.
     * <br>
     * Preview: <img src="https://tommyettinger.github.io/textratypist/previews/Monogram-standard.png" alt="Image preview" width="1200" height="675" />
     * (uses width=12, height=24, which is double the normal size)
     * <br>
     * Needs files:
     * <ul>
     *     <li><a href="https://github.com/tommyettinger/textratypist/blob/main/knownFonts/Monogram-standard.fnt">Monogram-standard.fnt</a></li>
     *     <li><a href="https://github.com/tommyettinger/textratypist/blob/main/knownFonts/Monogram-standard.png">Monogram-standard.png</a></li>
     *     <li><a href="https://github.com/tommyettinger/textratypist/blob/main/knownFonts/Monogram-License.txt">Monogram-License.txt</a></li>
     * </ul>
     *
     * @return the Font object that represents the 12px tall font Monogram
     */
    public Font getMonogram() {
        Font found = new Font("monogram.fnt", atlas.findRegion("monogram"), Font.DistanceFieldType.STANDARD, 0, 0, 0, 0, true);
            found
                .setDescent(-2.5f).setInlineImageMetrics(0f, 2f, -4f, 0.875f).setFancyLinePosition(0f, 3f)
                .useIntegerPositions(false).setBoldStrength(0.5f).setOutlineStrength(2.5f).setTextureFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest)
                .setUnderlineMetrics(-0.17f, 0.1f, -0.1f, -0.35f)
                .setStrikethroughMetrics(-0.17f, 0.18f, -0.1f, -0.2f)
                .setName("Monogram");
            return found;
    }

    /**
     * Returns a Font configured to use a small variable-width true-italic bitmap font with extensive coverage of
     * European scripts, <a href="https://datagoblin.itch.io/monogram">Monogram Italic</a>. Monogram (including Italic)
     * has good coverage of Unicode, including all of Greek, at least most of Cyrillic, and a good amount of extended
     * Latin. This does not scale well except to integer multiples, but it should look very crisp at its default size of
     * about 12 pixels tall with variable width. This should have equivalent metrics to {@link #getMonogram()}.
     * The Texture used for this is an unusual (and unusually small) size, 250x180, so that it is easier to pack into a
     * scene2d.ui Skin's atlas.
     * This defaults to having {@link Font#integerPosition} set to false, which is the usual default.
     * This may work well in a font family with other fonts that do not use a distance field effect.
     * <br>
     * Preview: <img src="https://tommyettinger.github.io/textratypist/previews/Monogram-Italic-standard.png" alt="Image preview" width="1200" height="675" />
     * (uses width=12, height=24, which is double the normal size)
     * <br>
     * Needs files:
     * <ul>
     *     <li><a href="https://github.com/tommyettinger/textratypist/blob/main/knownFonts/Monogram-Italic-standard.fnt">Monogram-Italic-standard.fnt</a></li>
     *     <li><a href="https://github.com/tommyettinger/textratypist/blob/main/knownFonts/Monogram-Italic-standard.png">Monogram-Italic-standard.png</a></li>
     *     <li><a href="https://github.com/tommyettinger/textratypist/blob/main/knownFonts/Monogram-License.txt">Monogram-License.txt</a></li>
     * </ul>
     *
     * @return the Font object that represents the 12px tall font Monogram Italic
     */
    public Font getMonogramItalic() {
        Font found = new Font("monogram-italic.fnt", atlas.findRegion("monogram-italic"), Font.DistanceFieldType.STANDARD, 0, 0, 0, 0, true);
        found
            .setDescent(-2.5f).setInlineImageMetrics(0f, 2f, -4f, 0.875f).setFancyLinePosition(0f, 3f)
            .useIntegerPositions(false).setBoldStrength(0.5f).setOutlineStrength(2.5f).setTextureFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest)
            .setUnderlineMetrics(-0.17f, 0.1f, -0.1f, -0.35f)
            .setStrikethroughMetrics(-0.17f, 0.18f, -0.1f, -0.2f)
            .setName("Monogram Italic");
        return found;
    }

    public Font getMonogramFamily() {
        Font.FontFamily family = new Font.FontFamily(
            new String[]{"Regular", "Italic"},
            new Font[]{getMonogram(), getMonogramItalic()});
        family.fontAliases.put("r", 0); // regular
        family.fontAliases.put("i", 1); // italic
        return family.connected[0].setFamily(family);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("Dawnlike.atlas"));
        font = getMonogramFamily();
        uiViewport = new ScreenViewport();
        stage = new Stage(uiViewport, batch);
        TypingLabel label = new TypingLabel("The [@i]City of Contempt[@r] has been The Kingdom's waste heap for a century.\n" +
            "Anything the Queen finds distasteful, the Cardinal sees as heretical, or the King looks at with contempt, goes to Contempt.\n" +
            "So naturally the brave, albeit grimy and rude, adventurers who slew the Ghoul Emperor and deserved praise had to be banished to Contempt.\n" +
            "After all, they couldn't look too good, otherwise the King's Army would look shabby, now wouldn't they?", font);
        label.setWrap(true);
        Table table = new Table();
        table.setFillParent(true);
        table.add(label).left().size(640, 480);
        stage.addActor(table);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        uiViewport.apply(false);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }

    @Override
    public void resize(int width, int height) {
        uiViewport.update(width, height, false);
    }
}
