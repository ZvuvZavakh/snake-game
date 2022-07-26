package zvuv.zavakh.game.snake.common;

import com.badlogic.ashley.core.ComponentMapper;
import zvuv.zavakh.game.snake.component.*;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<SnakeComponent> SNAKE =
            ComponentMapper.getFor(SnakeComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<DirectionComponent> DIRECTION =
            ComponentMapper.getFor(DirectionComponent.class);

    public static final ComponentMapper<CoinComponent> COIN =
            ComponentMapper.getFor(CoinComponent.class);

    public static final ComponentMapper<BodyPartComponent> BODY_PART =
            ComponentMapper.getFor(BodyPartComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<OrderZComponent> ORDER_Z =
            ComponentMapper.getFor(OrderZComponent.class);

    private Mappers() {}
}
