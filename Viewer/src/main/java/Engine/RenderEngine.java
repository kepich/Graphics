package Engine;

import Objects.Object;
import Utils.Color;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class RenderEngine {
    /**
     * Window size
     */
    private int WIDTH;
    private int HEIGHT;

    /**
     * Window handle
     */
    private long WINDOW;

    /**
     * Object list
     */
    private List<Object> objects;

    /**
     * @param window - Window handle
     * @param objects - Object list
     */
    public RenderEngine(long window, List<Object> objects){
        this.updateWindowSize(window);
        this.WINDOW = window;

        this.objects = objects;
    }

    private void updateWindowSize(long window){
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);
            this.WIDTH = pWidth.get();
            this.HEIGHT = pHeight.get();
        }
    }

    public void setPixel(int x, int y, Color color, int[] buffer) {
        buffer[this.WIDTH * y * 3 + x * 3] = color.red;
        buffer[this.WIDTH * y * 3 + x * 3 + 1] = color.green;
        buffer[this.WIDTH * y * 3 + x * 3 + 2] = color.blue;
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(WINDOW); // swap the color buffers
    }
}
