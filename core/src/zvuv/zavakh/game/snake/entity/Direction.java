package zvuv.zavakh.game.snake.entity;

public enum Direction {

    UP,
    DOWN,
    RIGHT,
    LEFT;

    public boolean isUp() {
        return this == UP;
    }

    public boolean isDown() {
        return this == DOWN;
    }

    public boolean isLeft() {
        return this == LEFT;
    }

    public boolean isRight() {
        return this == RIGHT;
    }

    public Direction getOppositeDirection() {
        if (isLeft()) {
            return RIGHT;
        } else if (isRight()) {
            return LEFT;
        } else if (isUp()) {
            return DOWN;
        } else if (isDown()) {
            return UP;
        }

        throw new IllegalArgumentException("Invalid direction");
    }

    public boolean isOppositeDirection(Direction direction) {
        return direction == getOppositeDirection();
    }
}
