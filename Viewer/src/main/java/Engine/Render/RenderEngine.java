package Engine.Render;

import Engine.Render.Camera.Camera;
import Engine.Logic.Objects.Object;
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

    /**
     * @param window  - Window handle
     * @param objects - Object list
     */
    public RenderEngine(long window, List<Object> objects) {
        this.updateWindowSize(window);
        this.WINDOW = window;
        this.objects = objects;

        this.camera = Camera.getCamera();
    }

    private void updateWindowSize(long window) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);
            this.WIDTH = pWidth.get();
            this.HEIGHT = pHeight.get();
        }
    }

    public void setPixels(Vector<Pixel> pixels) {
        for (Pixel pixel : pixels)
            if (isIsInViewField(pixel))
                setPixel(
                        pixel.getRelativePosition(this.WIDTH / 2, this.HEIGHT / 2).elementAt(0),
                        pixel.getRelativePosition(this.WIDTH / 2, this.HEIGHT / 2).elementAt(1),
                        pixel.getColor(),
                        this.buffer
                );
    }
    
    private boolean isIsInViewField(Pixel pixel){
        return pixel.getRelativePosition(this.WIDTH / 2, this.HEIGHT / 2).elementAt(0) >= 0 &&
                pixel.getRelativePosition(this.WIDTH / 2, this.HEIGHT / 2).elementAt(0) < this.WIDTH &&
                pixel.getRelativePosition(this.WIDTH / 2, this.HEIGHT / 2).elementAt(1) >= 0 &&
                pixel.getRelativePosition(this.WIDTH / 2, this.HEIGHT / 2).elementAt(1) < this.HEIGHT;
    }

    private void setPixel(int x, int y, Color color, int[] buffer) {
        buffer[this.WIDTH * y * 3 + x * 3] = color.red;
        buffer[this.WIDTH * y * 3 + x * 3 + 1] = color.green;
        buffer[this.WIDTH * y * 3 + x * 3 + 2] = color.blue;
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        allocateBuffer();
        fillBuffer(Color.GREY);

        for (Object element : this.objects)
            setPixels(element.draw());

        glDrawPixels(this.WIDTH, this.HEIGHT, GL_RGB, GL_INT, this.buffer);
        glfwSwapBuffers(this.WINDOW); // swap the color buffers
    }

    private void allocateBuffer() {
        this.buffer = new int[this.WIDTH * 3 * this.HEIGHT];
    }

    private void fillBuffer(Color color) {
        for (int i = 0; i < this.WIDTH * 3 * this.HEIGHT; i += 3) {
            this.buffer[i] = color.red;
            this.buffer[i + 1] = color.green;
            this.buffer[i + 2] = color.blue;
        }

    }
}
