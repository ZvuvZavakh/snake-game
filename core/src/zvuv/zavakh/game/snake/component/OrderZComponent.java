package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class OrderZComponent implements Component, Pool.Poolable {

    public int z = -1;

    @Override
    public void reset() {
        z = -1;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
