package com.github.tommyettinger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.github.tommyettinger.ds.IntObjectMap;
import com.github.tommyettinger.textra.Font;

public final class Assets implements LifecycleListener {
    public static Assets instance;
    private Assets() {
        Gdx.app.addLifecycleListener(this);
    }

    public static void initialize() {
        if(instance == null) instance = new Assets();
    }

    // Stores all images we use here efficiently, as well as the font image
    private TextureAtlas atlas;
    // This maps chars, such as '#', to specific images, such as a pillar.
    private IntObjectMap<TextureAtlas.AtlasRegion> charMapping;

    private Font font;

    public static TextureAtlas getAtlas() {
        initialize();
        if(instance.atlas == null){
            instance.atlas = new TextureAtlas(Gdx.files.internal("Dawnlike.atlas"));
        }
        return instance.atlas;
    }


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
    public static Font getMonogram() {
        initialize();
        Font f = new Font("monogram.fnt", getAtlas().findRegion("monogram"), Font.DistanceFieldType.STANDARD, 0, 0, 0, 0, true);
        f
            .setDescent(-2.5f).setInlineImageMetrics(0f, 2f, -4f, 0.875f).setFancyLinePosition(0f, 3f)
            .useIntegerPositions(false).setBoldStrength(0.5f).setOutlineStrength(2f).setTextureFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest)
            .setUnderlineMetrics(-0.5f, 0.1f, 0.1f, -0.35f)
            .setStrikethroughMetrics(-0.5f, 0.18f, 0.1f, -0.2f)
            .setName("Monogram")
            .scale(2);
        return f;
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
    public static Font getMonogramItalic() {
        initialize();
        Font f = new Font("monogram-italic.fnt", getAtlas().findRegion("monogram-italic"), Font.DistanceFieldType.STANDARD, 0, 0, 0, 0, true);
        f
            .setDescent(-2.5f).setInlineImageMetrics(0f, 2f, -4f, 0.875f).setFancyLinePosition(0f, 3f)
            .useIntegerPositions(false).setBoldStrength(0.5f).setOutlineStrength(2f).setTextureFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest)
            .setUnderlineMetrics(-0.5f, 0.1f, 0.1f, -0.35f)
            .setStrikethroughMetrics(-0.5f, 0.18f, 0.1f, -0.2f)
            .setName("Monogram Italic")
            .scale(2);
        return f;
    }

    public static Font getMonogramFamily() {
        initialize();
        if(instance.font == null) {
            Font.FontFamily family = new Font.FontFamily(
                new String[]{"Regular", "Italic"},
                new Font[]{getMonogram(), getMonogramItalic()});
            family.fontAliases.put("r", 0); // regular
            family.fontAliases.put("i", 1); // italic
            instance.font = family.connected[0].setFamily(family);
        }
        return instance.font;
    }


    /**
     * Called when the {@link Application} is about to pause
     */
    @Override
    public void pause() {

    }

    /**
     * Called when the Application is about to be resumed
     */
    @Override
    public void resume() {

    }

    /**
     * Called when the {@link Application} is about to be disposed
     */
    @Override
    public void dispose() {
        if(atlas != null){
            atlas.dispose();
            atlas = null;
        }
        if(font != null) {
            font.dispose();
            font = null;
        }

    }
}
