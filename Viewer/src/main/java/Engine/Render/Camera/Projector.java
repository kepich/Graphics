package Engine.Render.Camera;

import Engine.Logic.Objects.Vertex;
import Engine.Logic.Actions.Transformations.TransformationMatrixFactory;

import java.lang.module.Configuration;
import java.util.Vector;

import static Engine.Configurations.ALPHA_DISTORTION;

public class Projector {
    private Vertex position;
    private Vertex focus;

    private Vector<Vector<Float>> projectionMatrix;
    private ProjectionType type;
    private float alpha = 0f;

    public Projector(Vertex position, Vertex focus, ProjectionType type){
        this.position = position;
        this.focus = focus;
        this.type = type;
        this.projectionMatrix = updateProjectionMatrix();
    }

    private Vector<Vector<Float>> updateProjectionMatrix(){
        switch (this.type){
            case CABINET: return generateCabinetProjectionMatrix();
            case CAVALIER: return generateCavalierProjectionMatrix();
            default: return generatePerspectiveProjectionMatrix();
        }
    }

    private Vector<Vector<Float>> generatePerspectiveProjectionMatrix(){
        Vector<Vector<Float>> result = TransformationMatrixFactory.getEMatrix();
        result.elementAt(2).set(2, 0.0f);
        if (position.getCords().elementAt(2) != 0)
            result.elementAt(2).set(3, -1 / position.getCords().elementAt(2));
        if (position.getCords().elementAt(1) != 0)
            result.elementAt(1).set(3, -1 / position.getCords().elementAt(1));
        if (position.getCords().elementAt(0) != 0)
            result.elementAt(0).set(3, -1 / position.getCords().elementAt(0));

        return result;
    }

    private Vector<Vector<Float>> generateCavalierProjectionMatrix(){
        Vector<Vector<Float>> result = TransformationMatrixFactory.getEMatrix();
        result.elementAt(2).set(2, 0.0f);
        result.elementAt(2).set(0, (float) -Math.cos(ALPHA_DISTORTION));
        result.elementAt(2).set(1, (float) -Math.sin(ALPHA_DISTORTION));

        return result;
    }

    private Vector<Vector<Float>> generateCabinetProjectionMatrix(){
        Vector<Vector<Float>> result = TransformationMatrixFactory.getEMatrix();
        result.elementAt(2).set(2, 0.0f);
        result.elementAt(2).set(0, (float) -Math.cos(ALPHA_DISTORTION) / 2);
        result.elementAt(2).set(1, (float) -Math.sin(ALPHA_DISTORTION) / 2);

        return result;
    }

    public Vector<Vector<Float>> getProjectionMatrix(){
        return this.projectionMatrix;
    }
}
