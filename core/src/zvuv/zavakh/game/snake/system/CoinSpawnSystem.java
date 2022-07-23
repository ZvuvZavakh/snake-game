package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.CoinComponent;
import zvuv.zavakh.game.snake.component.PlayerComponent;
import zvuv.zavakh.game.snake.component.PositionComponent;
import zvuv.zavakh.game.snake.config.GameConfig;

public class CoinSpawnSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CoinComponent.class,
            PositionComponent.class
    ).get();

    public CoinSpawnSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CoinComponent coinComponent = Mappers.COIN.get(entity);
        PositionComponent positionComponent = Mappers.POSITION.get(entity);

        if (!coinComponent.isAvailable()) {
            float x = MathUtils.random((int) (GameConfig.MAX_Y - GameConfig.COIN_SIZE));
            float y = MathUtils.random((int) (GameConfig.MAX_Y - GameConfig.COIN_SIZE));

            positionComponent.setPosition(x, y);
            coinComponent.setAvailable(true);
        }
    }
}
