package Engine.Render.Camera;

import Engine.Configurations;
import Engine.Logic.Objects.Vertex;
import Engine.Render.Projector;

import java.util.Objects;

import static Utils.MatrixUtils.vm_mul;

public class Camera {
    private static Camera cameraSingleton;

    private Projector projector;

    public static Camera getCamera() {
        return Objects.requireNonNullElseGet(cameraSingleton, () -> cameraSingleton = new Camera());
    }

    private Camera() {
        this.projector = new Projector(Configurations.CAMERA_DEFAULT_POSITION, Configurations.CAMERA_DEFAULT_FOCUS, Configurations.CAMERA_DEFAULT_PROJECTION_TYPE);
    }

    public Vertex getPointProjection(Vertex point) {
        return new Vertex(vm_mul(point.getRawCords(), projector.getProjectionMatrix()));
    }
}
