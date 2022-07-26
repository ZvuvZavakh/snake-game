package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import zvuv.zavakh.game.snake.common.Direction;

public class DirectionComponent implements Component, Pool.Poolable {

    private static final Direction DEFAULT_DIRECTION = Direction.RIGHT;
    private Direction direction = DEFAULT_DIRECTION;

    @Override
    public void reset() {
        direction = DEFAULT_DIRECTION;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
