package zvuv.zavakh.game.snake.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.entity.BodyPart;
import zvuv.zavakh.game.snake.entity.Coin;
import zvuv.zavakh.game.snake.entity.EntityBase;
import zvuv.zavakh.game.snake.entity.SnakeHead;
import zvuv.zavakh.game.snake.util.GdxUtils;
import zvuv.zavakh.game.snake.util.ViewportUtils;
import zvuv.zavakh.game.snake.util.debug.DebugCameraController;

public class Renderer implements Disposable {

    private final Controller controller;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private DebugCameraController debugCameraController;

    public Renderer(Controller controller) {
        this.controller = controller;
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.clearScreen();
        viewport.apply();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        draw();
        shapeRenderer.end();

        renderDebug();
    }

    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, shapeRenderer);
        viewport.apply();

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
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
