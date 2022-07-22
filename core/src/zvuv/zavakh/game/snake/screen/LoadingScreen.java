package zvuv.zavakh.game.snake.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.common.AssetsDescriptors;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.util.GdxUtils;

public class LoadingScreen extends ScreenAdapter {

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f;
    private static final float PROGRESS_BAR_HEIGHT = 60f;
    private float progress;
    private float waitTime = 0.75f;
    private boolean ready;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private App app;

    public LoadingScreen(App app) {
        this.app = app;
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        
        update(delta);
        draw();

        if (ready) {
            app.setScreen(new GameScreen(app));
        }
    }

    private void draw() {
        viewport.apply();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(
                (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f,
                (GameConfig.HUD_WIDTH - PROGRESS_BAR_HEIGHT) / 2f,
                PROGRESS_BAR_WIDTH,
                PROGRESS_BAR_HEIGHT
        );
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(
                (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f,
                (GameConfig.HUD_WIDTH - PROGRESS_BAR_HEIGHT) / 2f,
                progress * PROGRESS_BAR_WIDTH,
                PROGRESS_BAR_HEIGHT
        );
        shapeRenderer.end();
    }

    private void update(float delta) {
        progress = app.getAssetManager().getProgress();

        if (app.getAssetManager().update()) {
            waitTime -= delta;

            if (waitTime <= 0f) {
                ready = true;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_WIDTH, camera);
        shapeRenderer = new ShapeRenderer();

        app.getAssetManager().load(AssetsDescriptors.UI_FONT);
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
