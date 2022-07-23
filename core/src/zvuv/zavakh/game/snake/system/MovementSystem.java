package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.MovementComponent;
import zvuv.zavakh.game.snake.component.PositionComponent;
import zvuv.zavakh.game.snake.component.SnakeComponent;
import zvuv.zavakh.game.snake.config.GameConfig;

public class MovementSystem extends IntervalIteratingSystem {

    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    public MovementSystem() {
        super(FAMILY, GameConfig.MOVE_TIME);
    }

    @Override
    protected void processEntity(Entity entity) {
        SnakeComponent snakeComponent = Mappers.SNAKE.get(entity);
        MovementComponent movementComponent = Mappers.MOVEMENT.get(snakeComponent.getHead());
        PositionComponent positionComponent = Mappers.POSITION.get(snakeComponent.getHead());
        float startX = positionComponent.getX();
        float startY = positionComponent.getY();

        positionComponent.setX(positionComponent.getX() + movementComponent.getxSpeed());
        positionComponent.setY(positionComponent.getY() + movementComponent.getySpeed());

        if (snakeComponent.getBodyParts().size > 0) {
            Entity tail = snakeComponent.getBodyParts().removeIndex(0);
            PositionComponent tailPositionComponent = Mappers.POSITION.get(tail);

            tailPositionComponent.setPosition(startX, startY);
            snakeComponent.getBodyParts().add(tail);
        }
    }
}
