package zvuv.zavakh.game.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import zvuv.zavakh.game.snake.screen.LoadingScreen;

public class App extends Game {

	private AssetManager assetManager;
	private SpriteBatch spriteBatch;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		spriteBatch = new SpriteBatch();

		setScreen(new LoadingScreen(this));
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		spriteBatch.dispose();
	}
}
