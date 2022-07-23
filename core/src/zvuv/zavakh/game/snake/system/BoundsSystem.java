package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.BoundsComponent;
import zvuv.zavakh.game.snake.component.DimensionComponent;
import zvuv.zavakh.game.snake.component.PositionComponent;

public class BoundsSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            BoundsComponent.class
    ).get();

    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION.get(entity);
        DimensionComponent dimensionComponent = Mappers.DIMENSION.get(entity);
        BoundsComponent boundsComponent = Mappers.BOUNDS.get(entity);

        boundsComponent.getBounds().setPosition(positionComponent.getX(), positionComponent.getY());
        boundsComponent.getBounds().setSize(dimensionComponent.getWidth(), dimensionComponent.getHeight());
    }
}
