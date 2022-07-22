package zvuv.zavakh.game.snake.common;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetsDescriptors {

    public static final AssetDescriptor<BitmapFont> UI_FONT =
            new AssetDescriptor<>(AssetsPaths.UI_FONT, BitmapFont.class);

    private AssetsDescriptors() {}
}
