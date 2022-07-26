package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.DirectionComponent;
import zvuv.zavakh.game.snake.component.PlayerComponent;
import zvuv.zavakh.game.snake.common.Direction;

public class PlayerControlSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    public PlayerControlSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        DirectionComponent directionComponent = Mappers.DIRECTION.get(entity);

        if (leftPressed) {
            updateDirection(directionComponent, Direction.LEFT);
        } else if (rightPressed) {
            updateDirection(directionComponent, Direction.RIGHT);
        } else if (upPressed) {
            updateDirection(directionComponent, Direction.UP);
        } else if (downPressed) {
            updateDirection(directionComponent, Direction.DOWN);
        }
    }

    private void updateDirection(DirectionComponent directionComponent, Direction direction) {
        if (!directionComponent.getDirection().isOppositeDirection(direction)) {
            directionComponent.setDirection(direction);
        }
    }
}
