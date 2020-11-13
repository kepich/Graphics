package Engine.Render;

import Engine.Logic.Objects.Object;
import Engine.Logic.Objects.Polygon;
import Engine.Logic.Objects.Segment;
import Engine.Logic.Objects.Vertex;
import Engine.Render.Camera.Camera;
import Utils.Color;
import Utils.MatrixUtils;
import Utils.Pixel;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Arrays;
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

    private int xCenter = WIDTH / 2;
    private int yCenter = HEIGHT / 2;

    /**
     * Window handle
     */
    private long WINDOW;

    /**
     * Engine.Logic.Objects.Object list
     */
    private List<Object> objects;

    private int[] buffer;
    private Camera camera;

    private boolean isLineObject = false;

    /**
     * @param window  - Window handle
     * @param objects - Engine.Logic.Objects.Object list
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
        // Сделать область с фигурами - самая левая и самая правая, верхняя и нижняя
        // Массив с глубинами - Z-буфер и буффер
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        allocateBuffer();
        fillBuffer(Color.GREY);

        if (this.isLineObject){
            for (Object element : this.objects)
                setPixels(element.draw());
        } else {
            drawObjectsWithZBuffer();
        }


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

    private void drawObjectsWithZBuffer(){
        int leftBoard = 1000000000;
        int rightBoard = -1000000000;
        int topBoard = 1000000000;
        int downBoard = -1000000000;

        for (Object obj : this.objects){
            for (Vertex vert: obj.getVertexes()){
                Vector<Float> cords = vert.getCords();
                leftBoard = (int) Math.min(cords.elementAt(0), leftBoard);
                rightBoard = (int) Math.max(cords.elementAt(0), rightBoard);
                topBoard = (int) Math.min(cords.elementAt(1), topBoard);
                downBoard = (int) Math.max(cords.elementAt(1), downBoard);
            }
        }

        int bufHeight = downBoard - topBoard;
        int bufWidth = rightBoard - leftBoard;
        double[][] zBuffer = new double[bufHeight + 1][bufWidth + 1];
        int[][] pixelsObjects = new int[bufHeight + 1][bufWidth + 1];

        for(int i = 0; i < bufHeight + 1; i++){
            Arrays.fill(zBuffer[i], 0, bufWidth + 1, Double.NEGATIVE_INFINITY);
            Arrays.fill(pixelsObjects[i], 0, bufWidth + 1, -1);
        }

        for (int i = 0; i < this.objects.size(); i++){
            Object object = this.objects.get(i);

            Polygon[] pols = object.getPolygons();
            String a = Arrays.toString(pols);
            for (int j = 0; j < object.getPolygons().length; j++){
                Polygon pol = object.getPolygons()[j];
                double[] plane = pol.getPlaneCords();

                int[] drawBox = getDrawBox(pol);
                double up = -(plane[0] * drawBox[0] + plane[1] * drawBox[2] + plane[3]), down = plane[2];
                double z;
                //z = up / down;

                for (int y = drawBox[2] - topBoard; y < (drawBox[3] - topBoard + 1); y++){
                    for (int x = drawBox[0] - leftBoard; x < (drawBox[1] - leftBoard + 1); x++){
                        if (isBelong(x + drawBox[0] - (drawBox[0] - leftBoard), y + drawBox[2] - (drawBox[2] - topBoard), pol)){
                            up = -(plane[0] * (leftBoard + x) + plane[1] * (topBoard + y) + plane[3]);
                            down = plane[2];
                            z = up / down;

                            if ((z > zBuffer[y][x])) {
                                zBuffer[y][x] = z;
                                pixelsObjects[y][x] = i * 10 + j;
                            }
                        }
                    }
                }
            }
        }

        for(int y = 0; y < bufHeight; y++){
            for(int x = 0; x < bufWidth; x++){
                if (pixelsObjects[y][x] != -1){
                    if ((y + topBoard < this.HEIGHT) && (y + topBoard > 0))
                    this.setPixel(
                            x + leftBoard,
                            y + topBoard,
                            this.objects.get(pixelsObjects[y][x] / 10).getPolygons()[pixelsObjects[y][x] % 10].getColor(),
                            this.buffer);
                }
            }
        }


    }

    private int[] getDrawBox(Object object){
        int leftBoard = 1000000000;
        int rightBoard = -1000000000;
        int topBoard = 1000000000;
        int downBoard = -1000000000;
        for (Vertex vert: object.getVertexes()){
            Vector<Float> cords = vert.getCords();
            leftBoard = (int) Math.min(cords.elementAt(0), leftBoard);
            rightBoard = (int) Math.max(cords.elementAt(0), rightBoard);
            topBoard = (int) Math.min(cords.elementAt(1), topBoard);
            downBoard = (int) Math.max(cords.elementAt(1), downBoard);
        }

        return new int[]{leftBoard, rightBoard, topBoard, downBoard};
    }

    private boolean isBelong(int x, int y, Polygon pol){
        Vector<Segment> segments = pol.getSegments();
        boolean c = false;
        Vector<Float> v1, v2;
        int v1x, v1y, v2x, v2y;

        for (Segment seg : segments){
            v1 = seg.getVertex1().getCords();
            v2 = seg.getVertex2().getCords();

            v1x = v1.get(0).intValue();
            v1y = v1.get(1).intValue();
            v2x = v2.get(0).intValue();
            v2y = v2.get(1).intValue();

            if ((((v2y <= y) && (y < v1y)) || ((v1y <= y) && (y < v2y))) && (x > ((v1x - v2x) * (y - v2y) / (v1y - v2y) + v2x))) {
                c = !c;
            }

        }
        return c;
    }
}