package zvuv.zavakh.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MovementComponent implements Component, Pool.Poolable {

    private float xSpeed;
    private float ySpeed;

    @Override
    public void reset() {
        xSpeed = 0f;
        ySpeed = 0f;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
}
