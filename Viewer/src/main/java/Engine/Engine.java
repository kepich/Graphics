package Engine;

import Engine.Logic.KeyController;
import Engine.Logic.LogicEngine;
import Engine.Render.RenderEngine;
import Engine.Logic.Objects.Object;

import java.util.ArrayList;

public class Engine {
    private RenderEngine renderEngine;
    private LogicEngine logicEngine;

    public Engine(long window) {
        ArrayList<Object> objects = new ArrayList<>();

        this.renderEngine = new RenderEngine(window, objects);
        this.logicEngine = new LogicEngine(objects, new KeyController(window, objects));
    }

    /**
     * Loop iteration
     */
    public void update() {
        this.logicEngine.update();
        this.renderEngine.render();
    }
}
