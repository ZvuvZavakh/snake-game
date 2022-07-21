package zvuv.zavakh.game.snake.entity;

import com.badlogic.gdx.utils.Array;
import zvuv.zavakh.game.snake.config.GameConfig;

public class Snake {

    private Direction direction = Direction.RIGHT;
    private SnakeHead snakeHead;
    private final Array<BodyPart> bodyParts = new Array<>();

    public Snake() {
        snakeHead = new SnakeHead();
    }

    public void move() {
        float xBeforeUpdate = snakeHead.getX();
        float yBeforeUpdate = snakeHead.getY();

        if (direction.isRight()) {
            snakeHead.updateX(GameConfig.SNAKE_SPEED);
        } else if (direction.isLeft()) {
            snakeHead.updateX(-GameConfig.SNAKE_SPEED);
        } else if (direction.isUp()) {
            snakeHead.updateY(GameConfig.SNAKE_SPEED);
        } else if (direction.isDown()) {
            snakeHead.updateY(-GameConfig.SNAKE_SPEED);
        }
        
        updateBodyParts(xBeforeUpdate, yBeforeUpdate);
    }

    public void reset() {
        bodyParts.clear();
        direction = Direction.RIGHT;
        snakeHead.setPosition(0f, 0f);
    }

    private void updateBodyParts(float xBeforeUpdate, float yBeforeUpdate) {
        if (bodyParts.size > 0) {
            BodyPart tail = bodyParts.removeIndex(0);

            tail.setPosition(xBeforeUpdate, yBeforeUpdate);
            bodyParts.add(tail);
        }
    }

    public void addBodyPart() {
        BodyPart bodyPart = new BodyPart();

        bodyPart.setPosition(snakeHead.getX(), snakeHead.getY());
        bodyParts.insert(0, bodyPart);
    }

    public void setDirection(Direction newDirection) {
        if (!direction.isOppositeDirection(newDirection) || bodyParts.size == 0) {
            direction = newDirection;
        }
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public Array<BodyPart> getBodyParts() {
        return bodyParts;
    }
}
