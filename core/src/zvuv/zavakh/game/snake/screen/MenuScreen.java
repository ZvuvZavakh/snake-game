package zvuv.zavakh.game.snake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.App;
import zvuv.zavakh.game.snake.common.AssetsDescriptors;
import zvuv.zavakh.game.snake.common.ButtonStyleNames;
import zvuv.zavakh.game.snake.common.RegionNames;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.util.GdxUtils;

public class MenuScreen extends ScreenAdapter {

    private final App app;
    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private TextureAtlas textureAtlas;

    public MenuScreen(App app) {
        this.app = app;
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, app.getSpriteBatch());
        skin = app.getAssetManager().get(AssetsDescriptors.UI_SKIN);
        textureAtlas = app.getAssetManager().get(AssetsDescriptors.GAMEPLAY_ATLAS);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(createUI());
    }

    private Actor createUI() {
        Table table = new Table(skin);

        TextureRegion backgroundRegion = textureAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));

        Image titleImage = new Image(skin, RegionNames.TITLE);

        Button playButton = new Button(skin, ButtonStyleNames.PLAY);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        Button quitButton = new Button(skin, ButtonStyleNames.QUIT);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        table.add(titleImage).row();
        table.add(playButton).row();
        table.add(quitButton).row();
        table.defaults().pad(10);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }

    private void quit() {
        Gdx.app.exit();
    }

    private void play() {
        app.setScreen(new GameScreen(app));
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
