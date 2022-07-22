package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DimensionComponent implements Component, Pool.Poolable {

    private float width = 1f;
    private float height = 1f;

    @Override
    public void reset() {
        width = 1f;
        height = 1f;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
