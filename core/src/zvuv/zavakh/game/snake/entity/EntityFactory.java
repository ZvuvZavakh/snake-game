package zvuv.zavakh.game.snake.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import zvuv.zavakh.game.snake.component.BoundsComponent;
import zvuv.zavakh.game.snake.component.DimensionComponent;
import zvuv.zavakh.game.snake.component.PositionComponent;
import zvuv.zavakh.game.snake.config.GameConfig;

public class EntityFactory {

    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public void getSnakeHead() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().setPosition(positionComponent.getX(), positionComponent.getY());
        boundsComponent.getBounds().setSize(dimensionComponent.getWidth(), dimensionComponent.getHeight());

        Entity shakeHead = engine.createEntity();
        shakeHead.add(positionComponent);
        shakeHead.add(dimensionComponent);
        shakeHead.add(boundsComponent);

        engine.addEntity(shakeHead);
    }
}
