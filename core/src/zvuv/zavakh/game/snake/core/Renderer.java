package zvuv.zavakh.game.snake.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.common.AssetsDescriptors;
import zvuv.zavakh.game.snake.common.GameManager;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.entity.BodyPart;
import zvuv.zavakh.game.snake.entity.EntityBase;
import zvuv.zavakh.game.snake.util.GdxUtils;
import zvuv.zavakh.game.snake.util.ViewportUtils;
import zvuv.zavakh.game.snake.util.debug.DebugCameraController;

public class Renderer implements Disposable {

    private static final float PADDING = 20.0f;

    private final Controller controller;
    private final App app;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer shapeRenderer;
    private DebugCameraController debugCameraController;
    private BitmapFont bitmapFont;
    private final GlyphLayout glyphLayout = new GlyphLayout();

    public Renderer(Controller controller, App app) {
        this.controller = controller;
        this.app = app;
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
        bitmapFont = app.getAssetManager().get(AssetsDescriptors.UI_FONT);
    }

    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.clearScreen();

        renderHud();
        renderDebug();
    }

    private void renderHud() {
        hudViewport.apply();
        app.getSpriteBatch().setProjectionMatrix(hudViewport.getCamera().combined);
        app.getSpriteBatch().begin();
        drawHud();
        app.getSpriteBatch().end();
    }

    private void drawHud() {
        String highScoreString = "HIGHSCORE: " + GameManager.INSTANCE.getDisplayHighScore();
        glyphLayout.setText(bitmapFont, highScoreString);
        bitmapFont.draw(
                app.getSpriteBatch(),
                glyphLayout,
                PADDING,
                hudViewport.getWorldHeight() - PADDING
        );

        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        float scoreX = hudViewport.getScreenWidth() - glyphLayout.width;
        float scoreY = hudViewport.getWorldHeight() - PADDING;
        glyphLayout.setText(bitmapFont, scoreString);
        bitmapFont.draw(
                app.getSpriteBatch(),
                glyphLayout,
                scoreX,
                scoreY
        );
    }

    private void renderDebug() {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, shapeRenderer);

        Color oldColor = new Color(shapeRenderer.getColor());

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        shapeRenderer.end();
        shapeRenderer.setColor(oldColor);
    }

    private void drawDebug() {
        for (BodyPart bodyPart : controller.getSnake().getBodyParts()) {
            drawEntity(bodyPart, Color.CHARTREUSE);
        }

        drawEntity(controller.getSnake().getSnakeHead(), Color.PINK);

        if (controller.getCoin().isAvailable()) {
            drawEntity(controller.getCoin(), Color.ORANGE);
        }
    }

    private void drawEntity(EntityBase entity, Color color) {
        shapeRenderer.setColor(color);

        Rectangle entityBounds = entity.getBounds();

        shapeRenderer.rect(
                entityBounds.getX(),
                entityBounds.getY(),
                entityBounds.getWidth(),
                entityBounds.getHeight()
        );
    }

    private void draw() {
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
