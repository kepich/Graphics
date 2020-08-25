package Utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        BigDecimal result = BigDecimal.ZERO;

        for (int i = 0; i < left.size(); i++)
            result = BigDecimal.valueOf(left.elementAt(i) * right.elementAt(i).elementAt(rightColumn)).add(result);
        return result.setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }

    public static Vector<Float> vm_mul(Vector<Float> left, Vector<Vector<Float>> right){
        Vector<Float> result = new Vector<>(0);
        for (int column = 0; column < right.size(); column++)
            result.add(vv_mul(left, right, column));

        return result;
    }

    public static Vector<Float> v_cords(Vector<Float> v1, Vector<Float> v2){
        Vector<Float> vectorCords = new Vector<>(0);

        vectorCords.add(v2.elementAt(0) - v1.elementAt(0));
        vectorCords.add(v2.elementAt(1) - v1.elementAt(1));
        return vectorCords;
    }

    public static Float vv_scalar(Vector<Float> v1, Vector<Float> v2){
        BigDecimal res = BigDecimal.ZERO;

        for(int i = 0; i < v1.size(); i++)
            res = BigDecimal.valueOf(v1.elementAt(i) * v2.elementAt(i)).add(res);

        return res.setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }

    public static double v_len(Vector<Float> v){
        BigDecimal res = BigDecimal.ZERO;
        for(int i = 0; i < v.size(); i++)
            res = BigDecimal.valueOf(v.elementAt(i) * v.elementAt(i)).add(res);
        var result = res.sqrt(MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN).floatValue();
        return result;
    }

    public static double vv_cos(Vector<Float> v1, Vector<Float> v2){
        double result = vv_scalar(v1, v2);
        result /= v_len(v1);
        result /= v_len(v2);
        result = BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_EVEN).floatValue();
        return result;
    }

    public static <T> void v_swap(Vector<T> v){
        T buf = v.elementAt(0);
        v.set(0, v.elementAt(1));
        v.set(1, buf);
    }
}
