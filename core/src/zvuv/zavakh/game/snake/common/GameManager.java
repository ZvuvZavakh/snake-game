package zvuv.zavakh.game.snake.common;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private GameState state = GameState.READY;
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;

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

    public void incrementScore(int amount) {
        score += amount;

        if (score >= highScore) {
            highScore = score;
        }
    }

    public void reset() {
        setPlaying();
        score = 0;
        displayScore = 0;
    }

    public void updateDisplayScore(float delta) {
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (int) (100 * delta));
        }

        if (displayHighScore < highScore) {
            displayHighScore = Math.min(highScore, displayHighScore + (int) (100 * delta));
        }
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }
}
