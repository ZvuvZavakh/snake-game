package zvuv.zavakh.game.snake.entity;

import com.badlogic.gdx.math.Rectangle;

public abstract class EntityBase {

    protected float x;
    protected float y;
    protected float width;
    protected float heigth;
    protected Rectangle bounds;

    public EntityBase() {
        bounds = new Rectangle(x, y, width, heigth);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize(float width, float heigth) {
        this.width = width;
        this.heigth = heigth;
        updateBounds();
    }

    public void setX(float x) {
        this.x = x;
        updateBounds();
    }

    public void setY(float y) {
        this.y = y;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private void updateBounds() {
        bounds.setPosition(x, y);
        bounds.setSize(width, heigth);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void updateX(float x) {
        this.x += x;
        updateBounds();
    }

    public void updateY(float y) {
        this.y += y;
        updateBounds();
    }
}
