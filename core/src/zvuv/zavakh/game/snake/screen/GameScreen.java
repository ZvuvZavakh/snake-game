package zvuv.zavakh.game.snake.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.common.AssetsDescriptors;
import zvuv.zavakh.game.snake.common.GameManager;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.system.EntityFactorySystem;
import zvuv.zavakh.game.snake.system.*;
import zvuv.zavakh.game.snake.system.debug.DebugCameraSystem;
import zvuv.zavakh.game.snake.system.debug.DebugRenderSystem;
import zvuv.zavakh.game.snake.system.debug.GridRenderSystem;
import zvuv.zavakh.game.snake.system.SnakeSystem;
import zvuv.zavakh.game.snake.util.GdxUtils;

public class GameScreen extends ScreenAdapter {

    private final App app;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer shapeRenderer;
    private PooledEngine engine;
    private BitmapFont bitmapFont;

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
        hudViewport.update(width, height, true);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        bitmapFont = app.getAssetManager().get(AssetsDescriptors.UI_FONT);
        engine = new PooledEngine();

        engine.addSystem(new EntityFactorySystem(app.getAssetManager()));
        engine.addSystem(new SoundSystem(app.getAssetManager()));
        engine.addSystem(new GridRenderSystem(viewport, shapeRenderer));
        engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
        engine.addSystem(new DebugRenderSystem(viewport, shapeRenderer));
        engine.addSystem(new DebugInputSystem());

        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSpawnSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new RenderSystem(viewport, app.getSpriteBatch()));
        engine.addSystem(new HudRenderSystem(hudViewport, app.getSpriteBatch(), bitmapFont));
        engine.addSystem(new StartUpSystem());

        GameManager.INSTANCE.reset();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        bitmapFont.dispose();
    }
}
