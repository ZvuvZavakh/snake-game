package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.PositionComponent;
import zvuv.zavakh.game.snake.component.WorldWrapComponent;
import zvuv.zavakh.game.snake.config.GameConfig;

public class WorldWrapSystem extends IteratingSystem {
    
    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class
    ).get();

    public WorldWrapSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION.get(entity);
        
        if (positionComponent.getX() >= GameConfig.WORLD_WIDTH) {
            positionComponent.setX(0f);
        } else if (positionComponent.getX() < 0f) {
            positionComponent.setX(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SIZE);
        }
        
        if (positionComponent.getY() >= GameConfig.MAX_Y) {
            positionComponent.setY(0f);
        } else if (positionComponent.getY() < 0f) {
            positionComponent.setY(GameConfig.MAX_Y - GameConfig.SNAKE_SIZE);
        }
    }
}
