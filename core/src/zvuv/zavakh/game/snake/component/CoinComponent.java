package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CoinComponent implements Component, Pool.Poolable {

    private boolean available;

    @Override
    public void reset() {
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
