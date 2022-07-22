package zvuv.zavakh.game.snake.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import zvuv.zavakh.game.snake.common.CollisionListener;
import zvuv.zavakh.game.snake.common.GameManager;
import zvuv.zavakh.game.snake.config.GameConfig;
import zvuv.zavakh.game.snake.entity.*;

public class Controller {

    private final CollisionListener collisionListener;
    private Snake snake;
    private Coin coin;
    private float timer;

    public Controller(CollisionListener collisionListener) {
        this.collisionListener = collisionListener;
        snake = new Snake();
        coin = new Coin();

        restart();
    }

    public void update(float delta) {
        GameManager.INSTANCE.updateDisplayScore(delta);

        if (GameManager.INSTANCE.getState().isPlaying()) {
            queryInput();
            timer += delta;

            if (timer >= GameConfig.MOVE_TIME) {
                timer = 0f;
                snake.move();
                checkOutOfBounds();
                checkCollisionWithCoin();
                checkCollisionWithBodyParts();
            }

            spawnCoin();
        }
    }

    private void restart() {
        GameManager.INSTANCE.reset();
        snake.reset();
        coin.setAvailable(false);
        timer = 0f;
    }

    private void checkCollisionWithBodyParts() {
        Rectangle snakeHeadBounds = snake.getSnakeHead().getBounds();

        for (BodyPart bodyPart : snake.getBodyParts()) {
            if (bodyPart.isJustAdded()) {
                bodyPart.setJustAdded(false);
                continue;
            }

            Rectangle bodyPartBounds = bodyPart.getBounds();

            if (Intersector.overlaps(snakeHeadBounds, bodyPartBounds)) {
                collisionListener.lose();
                GameManager.INSTANCE.updateHighScore();
                GameManager.INSTANCE.setGameOver();
                break;
            }
        }

    }

    private void checkCollisionWithCoin() {
        Rectangle snakeHeadBounds = snake.getSnakeHead().getBounds();
        Rectangle coinBounds = coin.getBounds();

        boolean overlaps = Intersector.overlaps(snakeHeadBounds, coinBounds);

        if (coin.isAvailable() && overlaps) {
            collisionListener.hitCoin();
            snake.addBodyPart();
            coin.setAvailable(false);
            GameManager.INSTANCE.incrementScore(GameConfig.COIN_VALUE);
        }
    }

    private void spawnCoin() {
        if (!coin.isAvailable()) {
            float x = MathUtils.random((int) (GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE));
            float y = MathUtils.random((int) (GameConfig.MAX_Y - GameConfig.COIN_SIZE));

            coin.setPosition(x, y);
            coin.setAvailable(true);
        }
    }

    private void checkOutOfBounds() {
        SnakeHead snakeHead = snake.getSnakeHead();

        if (snakeHead.getX() >= GameConfig.WORLD_WIDTH) {
            snakeHead.setX(0f);
        } else if (snakeHead.getX() < 0f) {
            snakeHead.setX(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SPEED);
        }

        if (snakeHead.getY() >= GameConfig.MAX_Y) {
            snakeHead.setY(0f);
        } else if (snakeHead.getY() < 0) {
            snakeHead.setY(GameConfig.MAX_Y - GameConfig.SNAKE_SPEED);
        }
    }

    private void queryInput() {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (leftPressed) {
            snake.setDirection(Direction.LEFT);
        } else if (rightPressed) {
            snake.setDirection(Direction.RIGHT);
        } else if (upPressed) {
            snake.setDirection(Direction.UP);
        } else if (downPressed) {
            snake.setDirection(Direction.DOWN);
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Coin getCoin() {
        return coin;
    }
}
