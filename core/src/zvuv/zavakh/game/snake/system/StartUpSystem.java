package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

public class StartUpSystem extends EntitySystem {

    private EntityFactorySystem entityFactorySystem;

    public StartUpSystem() {}

    @Override
    public boolean checkProcessing() {
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entityFactorySystem = engine.getSystem(EntityFactorySystem.class);
        start();
    }

    private void start() {
        entityFactorySystem.getBackground();
        entityFactorySystem.getSnake();
        entityFactorySystem.getCoin();
    }
}
