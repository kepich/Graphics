package Tranformation;

import Objects.Object;
import Objects.Vertex;
import Utils.MatrixUtils;

import java.util.Vector;

public abstract class Transform {
    private static void transformation(Object entity, Vector<Vector<Float>> matrix){
        Vector<Vector<Float>> vertexesCords = new Vector<>(0);
        Vector<Vertex> vertexes = entity.getVertexes();

        for (Vertex vertex: vertexes)
            vertexesCords.add(vertex.getCords());

        Vector<Vector<Float>> newCords = MatrixUtils.mm_mul(vertexesCords, matrix);

        for (int i = 0; i < vertexes.size(); i++)
            vertexes.elementAt(i).setPos(newCords.elementAt(i));
    }

    public static void offset(Object entity, Vector<Float> vector){
       transformation(entity, MatrixFactory.getOffsetMatrix(vector));
    }

    public static void rotateX(Object entity, float angle){
        transformation(entity, MatrixFactory.getRotateXMatrix(angle));
    }

    public static void rotateY(Object entity, float angle){
        transformation(entity, MatrixFactory.getRotateYMatrix(angle));
    }

    public static void rotateZ(Object entity, float angle){
        transformation(entity, MatrixFactory.getRotateZMatrix(angle));
    }

    public static void measureX(Object entity, float size){
        transformation(entity, MatrixFactory.getMeasureXMatrix(size));
    }

    public static void measureY(Object entity, float size){
        transformation(entity, MatrixFactory.getMeasureYMatrix(size));
    }

    public static void measureZ(Object entity, float size){
        transformation(entity, MatrixFactory.getMeasureZMatrix(size));
    }

    public static void measureComplex(Object entity, float size){
        transformation(entity, MatrixFactory.getMeasureComplexMatrix(size));
    }
}
