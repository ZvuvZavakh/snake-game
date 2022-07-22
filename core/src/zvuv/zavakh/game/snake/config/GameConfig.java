package zvuv.zavakh.game.snake.config;

public class GameConfig {

    private static final float Y_OFFSET = 2f;

    public static final float WIDTH = 800f;
    public static final float HEIGHT = 480f;

    public static final float WORLD_WIDTH = 25f;
    public static final float WORLD_HEIGHT = 15f;
    public static final float MAX_Y = WORLD_HEIGHT - Y_OFFSET;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;

    public static final float HUD_WIDTH = 800f;
    public static final float HUD_HEIGHT = 480f;

    public static final float SNAKE_SIZE = 1f;
    public static final float MOVE_TIME = 0.2f;
    public static final float SNAKE_SPEED = 1f;

    public static final float COIN_SIZE = 1f;
    public static final int COIN_VALUE = 20;

    private GameConfig() {
    }
}
