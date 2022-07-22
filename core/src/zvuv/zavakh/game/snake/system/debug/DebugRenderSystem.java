package zvuv.zavakh.game.snake.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.BoundsComponent;

public class DebugRenderSystem extends IteratingSystem {

    private static final Family DEBUG_FAMILY = Family.all(
            BoundsComponent.class
    ).get();

    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer shapeRenderer) {
        super(DEBUG_FAMILY);
        this.viewport = viewport;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = shapeRenderer.getColor();

        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setColor(Color.PINK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        super.update(deltaTime);
        shapeRenderer.end();
        shapeRenderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = Mappers.BOUNDS.get(entity);

        shapeRenderer.rect(
                boundsComponent.getBounds().getX(),
                boundsComponent.getBounds().getY(),
                boundsComponent.getBounds().getWidth(),
                boundsComponent.getBounds().getHeight()
        );
    }
}
