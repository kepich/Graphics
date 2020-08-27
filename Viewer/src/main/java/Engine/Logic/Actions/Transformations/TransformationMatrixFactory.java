package Engine.Logic.Actions.Transformations;

import java.util.Vector;

public abstract class TransformationMatrixFactory {
    public static Vector<Vector<Float>> getOffsetMatrix(Vector<Float> offset){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(3).set(0, offset.elementAt(0));
        result.elementAt(3).set(1, offset.elementAt(1));
        result.elementAt(3).set(2, offset.elementAt(2));

        return result;
    }

    public static Vector<Vector<Float>> getRotateXMatrix(float angle){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(1).set(1, (float)Math.cos(angle));
        result.elementAt(1).set(2, (float)Math.sin(angle));
        result.elementAt(2).set(1, -(float)Math.sin(angle));
        result.elementAt(2).set(2, (float)Math.cos(angle));

        return result;
    }

    public static Vector<Vector<Float>> getRotateZMatrix(float angle){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(0).set(0, (float)Math.cos(angle));
        result.elementAt(0).set(1, (float)Math.sin(angle));
        result.elementAt(1).set(0, -(float)Math.sin(angle));
        result.elementAt(1).set(1, (float)Math.cos(angle));

        return result;
    }

    public static Vector<Vector<Float>> getRotateYMatrix(float angle){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(0).set(0, (float)Math.cos(angle));
        result.elementAt(0).set(2, -(float)Math.sin(angle));
        result.elementAt(2).set(0, (float)Math.sin(angle));
        result.elementAt(2).set(2, (float)Math.cos(angle));

        return result;
    }

    public static Vector<Vector<Float>> getMeasureXMatrix(float multiplier){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(0).set(0, multiplier);

        return result;
    }

    public static Vector<Vector<Float>> getMeasureYMatrix(float multiplier){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(1).set(1, multiplier);

        return result;
    }

    public static Vector<Vector<Float>> getMeasureZMatrix(float multiplier){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(2).set(2, multiplier);

        return result;
    }

    public static Vector<Vector<Float>> getMeasureComplexMatrix(float multiplier){
        Vector<Vector<Float>> result = getEMatrix();
        result.elementAt(3).set(3, multiplier);

        return result;
    }

    public static Vector<Vector<Float>> getEMatrix(){
        Vector<Vector<Float>> result = new Vector<>(0);

        for (int i = 0; i < 4; i++){
            result.add(new Vector<>());
            for (int j = 0; j < 4; j++)
                result.lastElement().add((i == j) ? 1.0f : 0);
        }

        return result;
    }
}
