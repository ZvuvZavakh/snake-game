package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.common.GameManager;

public class HudRenderSystem extends EntitySystem {

    private static final float PADDING = 20f;

    private final Viewport hudViewport;
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private final GlyphLayout glyphLayout = new GlyphLayout();

    public HudRenderSystem(Viewport viewport, SpriteBatch spriteBatch, BitmapFont bitmapFont) {
        this.hudViewport = viewport;
        this.spriteBatch = spriteBatch;
        this.bitmapFont = bitmapFont;
    }

    @Override
    public void update(float deltaTime) {
        GameManager.INSTANCE.updateDisplayScore(deltaTime);
        hudViewport.apply();
        spriteBatch.setProjectionMatrix(hudViewport.getCamera().combined);
        spriteBatch.begin();

        String highScoreString = "HIGHSCORE: " + GameManager.INSTANCE.getDisplayHighScore();
        glyphLayout.setText(bitmapFont, highScoreString);
        bitmapFont.draw(
                spriteBatch,
                glyphLayout,
                PADDING,
                hudViewport.getWorldHeight() - PADDING
        );

        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        float scoreX = hudViewport.getScreenWidth() - glyphLayout.width;
        float scoreY = hudViewport.getWorldHeight() - PADDING;
        glyphLayout.setText(bitmapFont, scoreString);
        bitmapFont.draw(
                spriteBatch,
                glyphLayout,
                scoreX,
                scoreY
        );

        spriteBatch.end();
    }
}
