package zvuv.zavakh.game.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.util.ViewportUtils;

public class GridRenderSystem extends EntitySystem {

    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    public GridRenderSystem(Viewport viewport, ShapeRenderer shapeRenderer) {
        this.viewport = viewport;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, shapeRenderer);
    }
}
