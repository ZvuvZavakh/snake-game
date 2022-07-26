package zvuv.zavakh.game.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import zvuv.zavakh.game.snake.system.debug.DebugRenderSystem;
import zvuv.zavakh.game.snake.system.debug.GridRenderSystem;

public class DebugInputSystem extends EntitySystem {

    private boolean debugGrid = true;
    private boolean debugRender = true;
    private EntitySystem gridRenderSystem;
    private EntitySystem debugRenderSystem;

    public DebugInputSystem() {}

    @Override
    public void addedToEngine(Engine engine) {
        gridRenderSystem = engine.getSystem(GridRenderSystem.class);
        debugRenderSystem = engine.getSystem(DebugRenderSystem.class);
        toggleSystems();
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.F5)) {
            debugGrid = !debugGrid;
            toggleSystems();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F6)) {
            debugRender = !debugRender;
            toggleSystems();
        }
    }

    private void toggleSystems() {
        gridRenderSystem.setProcessing(debugGrid);
        debugRenderSystem.setProcessing(debugRender);
    }
}
