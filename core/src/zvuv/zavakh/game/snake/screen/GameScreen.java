package zvuv.zavakh.game.snake.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.common.AssetsPaths;
import zvuv.zavakh.game.snake.common.CollisionListener;
import zvuv.zavakh.game.snake.common.GameManager;
import zvuv.zavakh.game.snake.core.Controller;
import zvuv.zavakh.game.snake.core.Renderer;

public class GameScreen extends ScreenAdapter {

    private final App app;
    private final CollisionListener collisionListener;
    private Renderer renderer;
    private Controller controller;
    private Sound coinSound;
    private Sound loseSound;

    public GameScreen(final App app) {
        this.app = app;

        collisionListener = new CollisionListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            app.setScreen(new MenuScreen(app));
        }
    }

    @Override
    public void show() {
        controller = new Controller(collisionListener);
        renderer = new Renderer(controller, app);
        coinSound = app.getAssetManager().get(AssetsPaths.COIN_SOUND);
        loseSound = app.getAssetManager().get(AssetsPaths.LOSE_SOUND);
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
