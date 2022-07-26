package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import zvuv.zavakh.game.snake.common.GameManager;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.*;
import zvuv.zavakh.game.snake.config.GameConfig;

public class CollisionSystem extends IntervalSystem {

    private static final Family SNAKE_FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    private static final Family COIN_FAMILY = Family.all(
            CoinComponent.class
    ).get();

    private EntityFactorySystem entityFactorySystem;
    private SoundSystem soundSystem;

    public CollisionSystem() {
        super(GameConfig.MOVE_TIME);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entityFactorySystem = engine.getSystem(EntityFactorySystem.class);
        this.soundSystem = engine.getSystem(SoundSystem.class);
    }

    @Override
    protected void updateInterval() {
        ImmutableArray<Entity> snakes = getEngine().getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = getEngine().getEntitiesFor(COIN_FAMILY);

        for (Entity snake : snakes) {
            SnakeComponent snakeComponent = Mappers.SNAKE.get(snake);

            for (Entity bodyPart : snakeComponent.getBodyParts()) {
                BodyPartComponent bodyPartComponent = Mappers.BODY_PART.get(bodyPart);

                if (bodyPartComponent.isJustAdded()) {
                    bodyPartComponent.setJustAdded(false);
                    continue;
                }

                if (overlaps(snakeComponent.getHead(), bodyPart)) {
                    soundSystem.lose();
                    GameManager.INSTANCE.updateHighScore();
                    GameManager.INSTANCE.setGameOver();
                }
            }

            for (Entity coin : coins) {
                CoinComponent coinComponent = Mappers.COIN.get(coin);

                if (coinComponent.isAvailable() && overlaps(snakeComponent.getHead(), coin)) {
                    soundSystem.hitCoin();
                    coinComponent.setAvailable(false);
                    GameManager.INSTANCE.incrementScore(GameConfig.COIN_VALUE);

                    PositionComponent positionComponent = Mappers.POSITION.get(snakeComponent.getHead());
                    Entity bodyPart = entityFactorySystem.getBodyPart(positionComponent.getX(), positionComponent.getY());
                    snakeComponent.addBodyPart(bodyPart);
                }
            }
        }
    }

    private boolean overlaps(Entity entity1, Entity entity2) {
        BoundsComponent entity1BoundsComponent = Mappers.BOUNDS.get(entity1);
        BoundsComponent entity2BoundsComponent = Mappers.BOUNDS.get(entity2);

        return Intersector.overlaps(entity1BoundsComponent.getBounds(), entity2BoundsComponent.getBounds());
    }
}
