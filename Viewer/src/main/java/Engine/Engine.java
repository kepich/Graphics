package Engine;

import Engine.Logic.LogicEngine;
import Engine.Render.RenderEngine;
import Objects.Object;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class Engine {
    /**
     * Window handle
     */
    private long window;

    /**
     * Render engine
     */
    private RenderEngine renderEngine;

    /**
     * Logic engine
     */
    private LogicEngine logicEngine;

    /**
     * Objects list
     */

    private ArrayList<Object> objects = new ArrayList<>();

    public Engine(long window) {
        this.window = window;

        this.renderEngine = new RenderEngine(window, this.objects);   // Initialize render and logic engines
        this.logicEngine = new LogicEngine(this.objects);
    }

    /**
     * Loop iteration
     */
    public void update() {
        this.logicEngine.update();
        this.renderEngine.render();
    }
}
