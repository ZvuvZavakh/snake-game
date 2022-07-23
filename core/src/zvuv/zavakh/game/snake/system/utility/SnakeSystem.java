package zvuv.zavakh.game.snake.system.utility;

import com.badlogic.ashley.core.*;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.SnakeComponent;

// The purpose of this class is to handle removing linked entities to Snake class
public class SnakeSystem extends EntitySystem implements EntityListener {

    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    @Override
    public boolean checkProcessing() {
        // By returning false the "update" method will never be called in that system
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(FAMILY, this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        engine.removeEntityListener(this);
    }

    @Override
    public void entityRemoved(Entity entity) {
        SnakeComponent snakeComponent = Mappers.SNAKE.get(entity);
        Engine engine = getEngine();

        engine.removeEntity(snakeComponent.getHead());

        for (Entity bodyPart : snakeComponent.getBodyParts()) {
            engine.removeEntity(bodyPart);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        //Nothing going on here, so it does not need the actual implementation
    }
}
