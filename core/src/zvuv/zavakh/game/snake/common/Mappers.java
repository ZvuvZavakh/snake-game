package zvuv.zavakh.game.snake.common;

import com.badlogic.ashley.core.ComponentMapper;
import zvuv.zavakh.game.snake.component.BoundsComponent;
import zvuv.zavakh.game.snake.component.DimensionComponent;
import zvuv.zavakh.game.snake.component.PositionComponent;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    private Mappers() {}
}
