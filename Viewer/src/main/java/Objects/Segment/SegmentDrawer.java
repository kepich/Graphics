package Objects.Segment;

import Engine.Render.Camera.Camera;
import Engine.Render.RenderEngine;
import Objects.Vertex;

public abstract class SegmentDrawer{
    private static Camera camera = Camera.getCamera();

    /**
     * Brezenhem algorithm
     */
    public static void draw(Segment entity, RenderEngine renderEngine) {

        Vertex v1 = camera.getProjection(entity.getVertex1());
        Vertex v2 = camera.getProjection(entity.getVertex2());

        //renderEngine.setPixel();
    }
}
