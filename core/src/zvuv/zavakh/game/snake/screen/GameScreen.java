package zvuv.zavakh.game.snake.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.common.GameManager;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.entity.EntityFactory;
import zvuv.zavakh.game.snake.system.debug.DebugCameraSystem;
import zvuv.zavakh.game.snake.system.debug.DebugRenderSystem;
import zvuv.zavakh.game.snake.system.debug.GridRenderSystem;
import zvuv.zavakh.game.snake.util.GdxUtils;

public class GameScreen extends ScreenAdapter {

    private final App app;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private PooledEngine engine;
    private EntityFactory entityFactory;

    public GameScreen(App app) {
        this.app = app;
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            app.setScreen(new MenuScreen(app));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        engine = new PooledEngine();
        entityFactory = new EntityFactory(engine);

        engine.addSystem(new GridRenderSystem(viewport, shapeRenderer));
        engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
        engine.addSystem(new DebugRenderSystem(viewport, shapeRenderer));

        entityFactory.getSnakeHead();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
