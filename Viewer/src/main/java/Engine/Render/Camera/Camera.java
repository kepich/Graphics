package Engine.Render.Camera;

import Engine.Logic.Objects.Vertex;

import java.util.Objects;

import static Engine.Configurations.*;
import static Utils.MatrixUtils.vm_mul;

public class Camera {
    private static Camera cameraSingleton;

    private Projector projector;

    public static Camera getCamera() {
        return Objects.requireNonNullElseGet(cameraSingleton, () -> cameraSingleton = new Camera());
    }

    private Camera() {
        this.projector = new Projector(CAMERA_DEFAULT_POSITION, CAMERA_DEFAULT_FOCUS, CAMERA_DEFAULT_PROJECTION_TYPE);
    }

    public Vertex getPointProjection(Vertex point) {
        return new Vertex(vm_mul(point.getCords(), projector.getProjectionMatrix()));
    }
}
