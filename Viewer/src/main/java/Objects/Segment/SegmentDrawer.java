package Objects.Segment;

import Engine.Render.Camera.Camera;
import Engine.Render.Pixel;
import Engine.Render.RenderEngine;
import Objects.Vertex;

import java.util.Vector;

import static Utils.MatrixUtils.*;

public abstract class SegmentDrawer{
    private static Camera camera = Camera.getCamera();


    public static void draw(Segment entity, RenderEngine renderEngine) {
        Vertex v1 = camera.getProjection(entity.getVertex1());
        Vertex v2 = camera.getProjection(entity.getVertex2());

        Vector<Float> v1_cords = v1.getCords(), v2_cords = v2.getCords();

        Vector<Vector<Integer>> points;
        Vector<Float> vectorCords;

        vectorCords = v_cords(v1_cords, v2_cords);
        if (Math.abs(vv_cos(vectorCords, getVx())) > 0.5)
            points = getPixels(v1_cords, v2_cords);
        else{
            v_swap(v1_cords);
            v_swap(v2_cords);

            points = getPixels(v1_cords, v2_cords);

            for (Vector<Integer> v: points)
                v_swap(v);
        }

        Vector<Pixel> pixels = new Vector<>(0);
        for (Vector<Integer> point: points)
            pixels.add(new Pixel(point, entity.getColor()));

        renderEngine.setPixels(pixels);
    }

    private static Vector<Vector<Integer>> getPixels(Vector<Float> v1, Vector<Float> v2){
        if (v1.elementAt(0) > v2.elementAt(0))
            return drawLine(v2, v1);
        else
            return drawLine(v1, v2);
    }

    private static Vector<Float> getVx() {
        Vector<Float> vx = new Vector<>(0);
        vx.add(1.0f);
        vx.add(0.0f);

        return vx;
    }

    /**
     * Brezenhem algorithm
     */
    private static Vector<Vector<Integer>> drawLine(Vector<Float> left, Vector<Float> right){
        int deltax = Math.abs(right.elementAt(0).intValue() - left.elementAt(0).intValue());
        int deltay = Math.abs(right.elementAt(1).intValue() - left.elementAt(1).intValue());
        int error = 0;
        int deltaerr = (deltay + 1);
        int y = left.elementAt(1).intValue();
        int diry = right.elementAt(1).intValue() - left.elementAt(1).intValue();
        Vector<Vector<Integer>> result = new Vector<>(0);

        if (diry > 0)
            diry = 1;
        else if (diry < 0)
            diry = -1;

        for (int x = left.elementAt(0).intValue(); x < right.elementAt(0).intValue(); x++){
            result.add(new Vector(0));
            result.lastElement().add(x);
            result.lastElement().add(y);

            error = error + deltaerr;
            if (error >= (deltax + 1)){
                y = y + diry;
                error = error - (deltax + 1);
            }
        }

        return result;
    }
}
