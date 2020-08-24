package Engine.Render.Camera;

import Objects.Vertex;
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
        this.projectionMatrix = initializeProjectionMatrix();
    }

    private Vector<Vector<Float>> initializeProjectionMatrix() {
        Vector<Vector<Float>> result = new Vector<>(0);
        for (int i = 0; i < 4; i++){
            result.add(new Vector<>(0));
            for (int j = 0; j < 4; j++){
                result.lastElement().add((i == j) ? 1.0f: 0.0f);
            }
        }

        return result;
    }

    private Vector<Vector<Float>> updateProjectionMatrix(){
        //**************************************
        // TODO: Type check
        //**************************************

        return generateProjectionMatrix();
    }

    private Vector<Vector<Float>> generateProjectionMatrix(){
        Vector<Vector<Float>> result = new Vector<>(0);
        for (int i = 0; i < 4; i++){
            result.add(new Vector<>(0));
            for (int j = 0; j < 4; j++){
                result.lastElement().add((i == j) ? 1.0f: 0.0f);
            }
        }
        result.elementAt(2).set(2, 0.0f);
        result.elementAt(2).set(3, -1 / position.getCords().elementAt(2));  //Perspective transformation

        return result;
    }

    public Vector<Vector<Float>> getProjectionMatrix(){
        return this.projectionMatrix;
    }
}
