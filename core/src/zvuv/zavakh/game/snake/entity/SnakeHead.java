package zvuv.zavakh.game.snake.entity;

import zvuv.zavakh.game.snake.config.GameConfig;

public class SnakeHead extends EntityBase {

    private Direction direction = Direction.RIGHT;

    public SnakeHead() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }
}
