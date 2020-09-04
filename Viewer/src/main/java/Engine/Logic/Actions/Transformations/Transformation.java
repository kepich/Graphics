package Engine.Logic.Actions.Transformations;

import Engine.Logic.Objects.Object;
import Engine.Logic.Objects.Vertex;
import Utils.MatrixUtils;

import java.util.Vector;

public abstract class Transformation {
    private static void transformation(Object entity, Vector<Vector<Float>> matrix){
        if (entity.isMutable()){
            Vector<Vector<Float>> vertexesCords = new Vector<>(0);
            Vector<Vertex> vertexes = entity.getVertexes();

            for (Vertex vertex: vertexes)
                vertexesCords.add(vertex.getCords());

            Vector<Vector<Float>> newCords = MatrixUtils.mm_mul(vertexesCords, matrix);

            for (int i = 0; i < vertexes.size(); i++)
                vertexes.elementAt(i).setPos(newCords.elementAt(i));
        }
    }

    public static void offset(Object entity, Vector<Float> vector){
       transformation(entity, TransformationMatrixFactory.getOffsetMatrix(vector));
    }

    public static void rotateX(Object entity, float angle){
        transformation(entity, TransformationMatrixFactory.getRotateXMatrix(angle));
    }

    public static void rotateY(Object entity, float angle){
        transformation(entity, TransformationMatrixFactory.getRotateYMatrix(angle));
    }

    public static void rotateZ(Object entity, float angle){
        transformation(entity, TransformationMatrixFactory.getRotateZMatrix(angle));
    }

    public static void measureX(Object entity, float size){
        transformation(entity, TransformationMatrixFactory.getMeasureXMatrix(size));
    }

    public static void measureY(Object entity, float size){
        transformation(entity, TransformationMatrixFactory.getMeasureYMatrix(size));
    }

    public static void measureZ(Object entity, float size){
        transformation(entity, TransformationMatrixFactory.getMeasureZMatrix(size));
    }

    public static void measureComplex(Object entity, float size){
        transformation(entity, TransformationMatrixFactory.getMeasureComplexMatrix(size));
    }
}
