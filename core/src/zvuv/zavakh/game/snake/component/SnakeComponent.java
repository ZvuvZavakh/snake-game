package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class SnakeComponent implements Component, Pool.Poolable {

    private Entity head;
    private final Array<Entity> bodyParts = new Array<>();


    @Override
    public void reset() {
        head = null;
        bodyParts.clear();
    }

    public Entity getHead() {
        return head;
    }

    public void setHead(Entity head) {
        this.head = head;
    }

    public Array<Entity> getBodyParts() {
        return bodyParts;
    }

    public void addBodyPart(Entity bodyPart) {
        bodyParts.insert(0, bodyPart);
    }
}
