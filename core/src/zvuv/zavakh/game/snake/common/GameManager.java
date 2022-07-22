package zvuv.zavakh.game.snake.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();
    public static final boolean debugEnabled = false;
    private static final String HIGH_SCORE_KEY = "highscore";

    private Preferences preferences;
    private GameState state = GameState.READY;
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;

    private GameManager() {
        preferences = Gdx.app.getPreferences("gamedata");
        highScore = preferences.getInteger(HIGH_SCORE_KEY, 0);
        displayHighScore = highScore;
    }

    public void setPlaying() {
        state = GameState.PLAYING;
    }

    public void setGameOver() {
        state = GameState.GAME_OVER;
    }

    public boolean isGameOver() {
        return state == GameState.GAME_OVER;
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

    public void updateHighScore() {
        if (score < highScore) {
            return;
        }

        highScore = score;
        preferences.putInteger(HIGH_SCORE_KEY, highScore);
        preferences.flush();
    }

    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }
}
