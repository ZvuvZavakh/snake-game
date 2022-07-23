package zvuv.zavakh.game.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import zvuv.zavakh.game.snake.component.*;
import zvuv.zavakh.game.snake.config.GameConfig;

public class EntityFactory {

    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity getSnakeHead() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().setPosition(positionComponent.getX(), positionComponent.getY());
        boundsComponent.getBounds().setSize(dimensionComponent.getWidth(), dimensionComponent.getHeight());

        DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class);
        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        WorldWrapComponent worldWrapComponent = engine.createComponent(WorldWrapComponent.class);
        Entity shakeHead = engine.createEntity();
        shakeHead.add(positionComponent);
        shakeHead.add(dimensionComponent);
        shakeHead.add(boundsComponent);
        shakeHead.add(directionComponent);
        shakeHead.add(movementComponent);
        shakeHead.add(playerComponent);
        shakeHead.add(worldWrapComponent);

        engine.addEntity(shakeHead);

        return shakeHead;
    }

    public Entity getSnake() {
        SnakeComponent snakeComponent = engine.createComponent(SnakeComponent.class);
        snakeComponent.setHead(getSnakeHead());

        Entity snake = engine.createEntity();
        snake.add(snakeComponent);

        engine.addEntity(snake);

        return snake;
    }

    public Entity getBodyPart(float x, float y) {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        positionComponent.setPosition(x, y);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().setPosition(positionComponent.getX(), positionComponent.getY());
        boundsComponent.getBounds().setSize(dimensionComponent.getWidth(), dimensionComponent.getHeight());

        BodyPartComponent bodyPartComponent = engine.createComponent(BodyPartComponent.class);

        Entity bodyPart = engine.createEntity();
        bodyPart.add(positionComponent);
        bodyPart.add(dimensionComponent);
        bodyPart.add(boundsComponent);
        bodyPart.add(bodyPartComponent);

        engine.addEntity(bodyPart);

        return bodyPart;
    }

    public void getCoin() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);

        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        boundsComponent.getBounds().setPosition(positionComponent.getX(), positionComponent.getY());
        boundsComponent.getBounds().setSize(dimensionComponent.getWidth(), dimensionComponent.getHeight());

        CoinComponent coinComponent = engine.createComponent(CoinComponent.class);

        Entity coin = engine.createEntity();
        coin.add(positionComponent);
        coin.add(dimensionComponent);
        coin.add(boundsComponent);
        coin.add(coinComponent);

        engine.addEntity(coin);
    }
}
