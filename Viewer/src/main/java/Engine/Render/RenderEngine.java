package Engine.Render;

import Engine.Render.Camera.Camera;
import Engine.Render.Camera.Projector;
import Objects.Object;
import Objects.Vertex;
import Utils.Color;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.List;
import java.util.Vector;

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

    private int[] buffer;
    private Camera camera;
    private Projector projector;

    /**
     * @param window - Window handle
     * @param objects - Object list
     */
    public RenderEngine(long window, List<Object> objects){
        this.updateWindowSize(window);
        this.WINDOW = window;
        this.projector = new Projector(new Vertex(0, 0, 5, 0.0f), new Vertex(0, 0, 0, 0.0f), 1);
        this.objects = objects;

        this.camera = Camera.getCamera(projector);
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

    public void setPixels(Vector<Pixel> pixels){
        for (Pixel pixel: pixels)
            if((pixel.getPosition().elementAt(0) >= 0 && pixel.getPosition().elementAt(0) < this.WIDTH) &&
                    (pixel.getPosition().elementAt(1) >= 0 && pixel.getPosition().elementAt(1) < this.HEIGHT)
            )
                setPixel(
                        pixel.getPosition().elementAt(0),
                        pixel.getPosition().elementAt(1),
                        pixel.getColor(),
                        this.buffer
                );
    }

    public void setPixel(int x, int y, Color color, int[] buffer) {
        buffer[this.WIDTH * y * 3 + x * 3] = color.red;
        buffer[this.WIDTH * y * 3 + x * 3 + 1] = color.green;
        buffer[this.WIDTH * y * 3 + x * 3 + 2] = color.blue;
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        allocateBuffer();

        for (Object element: this.objects)
            element.draw(this);

        glDrawPixels(this.WIDTH, this.HEIGHT, GL_RGB, GL_INT, this.buffer);
        glfwSwapBuffers(WINDOW); // swap the color buffers
    }

    private int[] allocateBuffer(){
        return this.buffer = new int[this.WIDTH * 3 * this.HEIGHT];
    }
}
