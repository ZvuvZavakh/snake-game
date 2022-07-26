package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.snake.common.Mappers;
import zvuv.zavakh.game.snake.component.DimensionComponent;
import zvuv.zavakh.game.snake.component.OrderZComponent;
import zvuv.zavakh.game.snake.component.PositionComponent;
import zvuv.zavakh.game.snake.component.TextureComponent;
import zvuv.zavakh.game.snake.util.OrderZComparator;

public class RenderSystem extends SortedIteratingSystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            TextureComponent.class,
            OrderZComponent.class
    ).get();

    private final SpriteBatch spriteBatch;
    private final Viewport viewport;

    public RenderSystem(Viewport viewport, SpriteBatch spriteBatch) {
        super(FAMILY, OrderZComparator.INSTANCE);
        this.spriteBatch = spriteBatch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        super.update(deltaTime);
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.POSITION.get(entity);
        DimensionComponent dimensionComponent = Mappers.DIMENSION.get(entity);
        TextureComponent textureComponent = Mappers.TEXTURE.get(entity);

        spriteBatch.draw(
                textureComponent.getTextureRegion(),
                positionComponent.getX(),
                positionComponent.getY(),
                dimensionComponent.getWidth(),
                dimensionComponent.getHeight()
        );
    }
}
