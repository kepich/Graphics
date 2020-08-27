package Engine.Adapters;

import Engine.Logic.Objects.Segment;
import Engine.Render.Camera.Camera;
import Engine.Render.Pixel;

import java.util.Vector;

import static Utils.MatrixUtils.*;

public abstract class SegmentAdapter {
    private static Camera camera = Camera.getCamera();

    public static Vector<Pixel> draw(Segment entity) {
        Vector<Float> v1_cords = camera.getPointProjection(entity.getVertex1()).normalize().getCords();
        Vector<Float> v2_cords = camera.getPointProjection(entity.getVertex2()).normalize().getCords();

        Vector<Pixel> pixels = new Vector<>();
        for (Vector<Integer> point: getSegmentRaster(v1_cords, v2_cords))
            pixels.add(new Pixel(point, entity.getColor()));

        return pixels;
    }

    private static boolean isZeroVector(Vector<Float> v1_cords, Vector<Float> v2_cords){
        Vector<Float> vectorCords = v_cords(v1_cords, v2_cords);
        return vectorCords.elementAt(0) != 0 || vectorCords.elementAt(1) != 0;
    }

    private static Vector<Vector<Integer>> getSegmentRaster(Vector<Float> v1_cords, Vector<Float> v2_cords){
        Vector<Vector<Integer>> points = new Vector<>();

        if(isZeroVector(v1_cords, v2_cords))
            if (isHorizontalLine(v1_cords, v2_cords))
                points = getRaster(v1_cords, v2_cords);
            else{
                v_swap(v1_cords);
                v_swap(v2_cords);

                points = getRaster(v1_cords, v2_cords);

                for (Vector<Integer> v: points)
                    v_swap(v);
            }

        return points;
    }

    private static boolean isHorizontalLine(Vector<Float> v1_cords, Vector<Float> v2_cords){
        return Math.abs(v1_cords.elementAt(0) - v2_cords.elementAt(0)) >
                Math.abs(v1_cords.elementAt(1) - v2_cords.elementAt(1));
    }

    private static Vector<Vector<Integer>> getRaster(Vector<Float> v1, Vector<Float> v2){
        if (v1.elementAt(0) > v2.elementAt(0)){
            return drawLine(v2, v1);
        }
        else{
            return drawLine(v1, v2);
        }

    }

    /**
     * Brezenhem algorithm
     */
    private static Vector<Vector<Integer>> drawLine(Vector<Float> left, Vector<Float> right){
        int deltaX = Math.abs(right.elementAt(0).intValue() - left.elementAt(0).intValue());
        int deltaY = Math.abs(right.elementAt(1).intValue() - left.elementAt(1).intValue());
        int error = 0;
        int deltaErr = (deltaY + 1);
        int y = left.elementAt(1).intValue();
        int direction = right.elementAt(1).intValue() - left.elementAt(1).intValue();
        Vector<Vector<Integer>> result = new Vector<>(0);

        if (direction > 0)
            direction = 1;
        else if (direction < 0)
            direction = -1;

        for (int x = left.elementAt(0).intValue(); x < right.elementAt(0).intValue(); x++){
            Vector<Integer> newElement = new Vector<>();
            newElement.add(x);
            newElement.add(y);

            result.add(newElement);

            error = error + deltaErr;
            if (error >= (deltaX + 1)){
                y = y + direction;
                error = error - (deltaX + 1);
            }
        }

        return result;
    }
}
