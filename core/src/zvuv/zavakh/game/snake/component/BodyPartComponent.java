package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class BodyPartComponent implements Component, Pool.Poolable {

    private boolean justAdded = true;

    @Override
    public void reset() {
        justAdded = true;
    }

    public boolean isJustAdded() {
        return justAdded;
    }

    public void setJustAdded(boolean justAdded) {
        this.justAdded = justAdded;
    }
}
