package zvuv.zavakh.game.snake.common;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private GameState state = GameState.READY;

    private GameManager() {}

    public void setPlaying() {
        state = GameState.PLAYING;
    }

    public void setGameOver() {
        state = GameState.GAME_OVER;
    }

    public GameState getState() {
        return state;
    }
}
