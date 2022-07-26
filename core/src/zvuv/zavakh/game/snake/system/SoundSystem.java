package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import zvuv.zavakh.game.snake.common.AssetsDescriptors;

public class SoundSystem extends EntitySystem {

    private final AssetManager assetManager;
    private Sound coinSound;
    private Sound loseSound;

    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        coinSound = assetManager.get(AssetsDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetsDescriptors.LOSE_SOUND);
    }

    @Override
    public boolean checkProcessing() {
        return false;
    }

    public void hitCoin() {
        coinSound.play();
    }

    public void lose() {
        loseSound.play();
    }
}
