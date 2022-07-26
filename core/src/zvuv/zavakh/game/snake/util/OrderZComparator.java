package zvuv.zavakh.game.snake.util;

import com.badlogic.ashley.core.Entity;
import zvuv.zavakh.game.snake.common.Mappers;

import java.util.Comparator;

public class OrderZComparator implements Comparator<Entity> {

    public static final OrderZComparator INSTANCE = new OrderZComparator();

    private OrderZComparator() {}

    @Override
    public int compare(Entity entity1, Entity entity2) {
        return Integer.compare(
                Mappers.ORDER_Z.get(entity1).getZ(),
                Mappers.ORDER_Z.get(entity2).getZ()
        );
    }
}
