package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class BoundsComponent implements Component, Pool.Poolable {

    private final Rectangle bounds = new Rectangle(0f, 0f, 1f, 1f);

    @Override
    public void reset() {
        bounds.setPosition(0f, 0f);
        bounds.setSize(1f, 1f);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
