package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import zvuv.zavakh.game.snake.common.AssetsDescriptors;
import zvuv.zavakh.game.snake.common.RegionNames;
import zvuv.zavakh.game.snake.component.*;
import zvuv.zavakh.game.snake.config.GameConfig;

public class EntityFactorySystem extends EntitySystem {

    private static final int BACKGROUND_ORDER_Z = 0;
    private static final int COIN_ORDER_Z = 1;
    private static final int BODY_ORDER_Z = 2;
    private static final int HEAD_ORDER_Z = 3;

    private final TextureAtlas textureAtlas;
    private PooledEngine engine;

    public EntityFactorySystem(AssetManager assetManager) {
        textureAtlas = assetManager.get(AssetsDescriptors.GAMEPLAY_ATLAS);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    @Override
    public void update(float deltaTime) {
        // NO PROCESSING
    }

    @Override
    public boolean checkProcessing() {
        return false;
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

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.HEAD));

        OrderZComponent orderZComponent = engine.createComponent(OrderZComponent.class);
        orderZComponent.setZ(HEAD_ORDER_Z);

        Entity shakeHead = engine.createEntity();
        shakeHead.add(positionComponent);
        shakeHead.add(dimensionComponent);
        shakeHead.add(boundsComponent);
        shakeHead.add(directionComponent);
        shakeHead.add(movementComponent);
        shakeHead.add(playerComponent);
        shakeHead.add(worldWrapComponent);
        shakeHead.add(textureComponent);
        shakeHead.add(orderZComponent);

        engine.addEntity(shakeHead);

        return shakeHead;
    }

    public void getSnake() {
        SnakeComponent snakeComponent = engine.createComponent(SnakeComponent.class);
        snakeComponent.setHead(getSnakeHead());

        Entity snake = engine.createEntity();
        snake.add(snakeComponent);

        engine.addEntity(snake);
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

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.BODY));

        OrderZComponent orderZComponent = engine.createComponent(OrderZComponent.class);
        orderZComponent.setZ(BODY_ORDER_Z);

        Entity bodyPart = engine.createEntity();
        bodyPart.add(positionComponent);
        bodyPart.add(dimensionComponent);
        bodyPart.add(boundsComponent);
        bodyPart.add(bodyPartComponent);
        bodyPart.add(textureComponent);
        bodyPart.add(orderZComponent);

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

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.COIN));

        OrderZComponent orderZComponent = engine.createComponent(OrderZComponent.class);
        orderZComponent.setZ(COIN_ORDER_Z);

        Entity coin = engine.createEntity();
        coin.add(positionComponent);
        coin.add(dimensionComponent);
        coin.add(boundsComponent);
        coin.add(coinComponent);
        coin.add(textureComponent);
        coin.add(orderZComponent);

        engine.addEntity(coin);
    }

    public void getBackground() {
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);

        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        dimensionComponent.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        textureComponent.setTextureRegion(textureAtlas.findRegion(RegionNames.BACKGROUND));

        OrderZComponent orderZComponent = engine.createComponent(OrderZComponent.class);
        orderZComponent.setZ(BACKGROUND_ORDER_Z);

        Entity background = engine.createEntity();
        background.add(positionComponent);
        background.add(dimensionComponent);
        background.add(textureComponent);
        background.add(orderZComponent);

        engine.addEntity(background);
    }
}
