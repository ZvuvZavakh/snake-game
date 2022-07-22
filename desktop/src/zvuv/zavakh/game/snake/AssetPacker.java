package zvuv.zavakh.game.snake;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    private static final String rawPathPrefix = "assets/assets-raw/";
    private static final String destinationPathPrefix = "assets/";


    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        TexturePacker.process(
                settings,
                rawPathPrefix + "gameplay",
                destinationPathPrefix + "gameplay",
                "gameplay"
        );

        TexturePacker.process(
                settings,
                rawPathPrefix + "ui",
                destinationPathPrefix + "ui",
                "ui"
        );
    }
}
