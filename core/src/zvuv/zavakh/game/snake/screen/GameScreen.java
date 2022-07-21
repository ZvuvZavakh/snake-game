package zvuv.zavakh.game.snake.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.core.Controller;
import zvuv.zavakh.game.snake.core.Renderer;

public class GameScreen extends ScreenAdapter {

    private final App app;
    private final AssetManager assetManager;
    private Renderer renderer;
    private Controller controller;

    public GameScreen(App app) {
        this.app = app;
        assetManager = app.getAssetManager();
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void show() {
        controller = new Controller();
        renderer = new Renderer(controller);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }
}
