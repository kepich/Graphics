package Engine.Render.Camera;

import Objects.Vertex;

import java.util.Objects;

import static Utils.MatrixUtils.vm_mul;

public class Camera {
    private Projector projector;

    private static Camera cameraSingleton;

    private Camera(){ /* Private constructor*/ }

    private Camera(Projector projector){
        this.projector = projector;
    }

    public static Camera getCamera(Projector projector){
        return Objects.requireNonNullElseGet(cameraSingleton, () -> cameraSingleton = new Camera(projector));
    }

    public static Camera getCamera(){
        return cameraSingleton;
    }

    public Vertex getProjection(Vertex point){
        return new Vertex(vm_mul(point.getCords(), projector.getProjectionMatrix()));
    }
}
