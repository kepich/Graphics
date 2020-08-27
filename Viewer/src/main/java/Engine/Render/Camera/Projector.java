package Engine.Render.Camera;

import Engine.Logic.Objects.Vertex;
import Engine.Logic.Actions.Transformations.TransformationMatrixFactory;

import java.util.Vector;

public class Projector {
    private Vertex position;
    private Vertex focus;

    private Vector<Vector<Float>> projectionMatrix;
    private int type;

    public Projector(Vertex position, Vertex focus, int type){
        this.position = position;
        this.focus = focus;
        this.type = type;
        this.projectionMatrix = updateProjectionMatrix();
    }

    private Vector<Vector<Float>> updateProjectionMatrix(){
        //**************************************
        // TODO: Type check
        //**************************************

        return generateProjectionMatrix();
    }

    private Vector<Vector<Float>> generateProjectionMatrix(){
        Vector<Vector<Float>> result = TransformationMatrixFactory.getEMatrix();
        result.elementAt(2).set(2, 0.0f);
        if (position.getCords().elementAt(2) != 0)
            result.elementAt(2).set(3, -1 / position.getCords().elementAt(2));  //Perspective transformation
        if (position.getCords().elementAt(1) != 0)
            result.elementAt(1).set(3, -1 / position.getCords().elementAt(1));  //Perspective transformation
        if (position.getCords().elementAt(0) != 0)
            result.elementAt(0).set(3, -1 / position.getCords().elementAt(0));  //Perspective transformation

        return result;
    }

    public Vector<Vector<Float>> getProjectionMatrix(){
        return this.projectionMatrix;
    }
}
