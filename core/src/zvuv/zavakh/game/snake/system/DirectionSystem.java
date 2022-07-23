package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.DirectionComponent;
import zvuv.zavakh.game.snake.component.MovementComponent;
import zvuv.zavakh.game.snake.config.GameConfig;

public class DirectionSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            DirectionComponent.class,
            MovementComponent.class
    ).get();

    public DirectionSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent directionComponent = Mappers.DIRECTION.get(entity);
        MovementComponent movementComponent = Mappers.MOVEMENT.get(entity);

        movementComponent.setxSpeed(0f);
        movementComponent.setySpeed(0f);

        if (directionComponent.getDirection().isRight()) {
            movementComponent.setxSpeed(GameConfig.SNAKE_SPEED);
        } else if (directionComponent.getDirection().isLeft()) {
            movementComponent.setxSpeed(-GameConfig.SNAKE_SPEED);
        } else if (directionComponent.getDirection().isUp()) {
            movementComponent.setySpeed(GameConfig.SNAKE_SPEED);
        } else if (directionComponent.getDirection().isDown()) {
            movementComponent.setySpeed(-GameConfig.SNAKE_SPEED);
        }
    }
}
