package Utils;

import java.util.Vector;

public abstract class MatrixUtils {
    public static Vector<Vector<Float>> mm_mul(Vector<Vector<Float>> left, Vector<Vector<Float>> right){
        Vector<Vector<Float>> result = new Vector<>(0);
        for (Vector<Float> row: left){
            result.add(new Vector<>(0));

            for (int column = 0; column < right.size(); column++)
                result.lastElement().add(vv_mul(row, right, column));
        }

        return result;
    }

    public static Float vv_mul(Vector<Float> left, Vector<Vector<Float>> right, int rightColumn){
        float result = 0.0f;
        for (Float i: left) {
            for (Float j: right.elementAt(rightColumn)) {
                result += i * j;
            }
        }

        return result;
    }

    public static Vector<Float> vm_mul(Vector<Float> left, Vector<Vector<Float>> right){
        Vector<Float> result = new Vector<>(0);
        for (int column = 0; column < right.size(); column++)
            result.add(vv_mul(left, right, column));

        return result;
    }
}
