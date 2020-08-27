package Engine;

import Engine.Logic.Objects.Vertex;

public abstract class Configurations {
    public static final float MOVEMENT_SPEED = 4f;
    public static final float ROTATION_SPEED = 0.1f;

    public static final float H_NORMALISATION = 1;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final String WINDOW_TITLE = "Viewer";

    public static final Vertex CAMERA_DEFAULT_POSITION = new Vertex(0, 0, 1000, 0.0f);
    public static final Vertex CAMERA_DEFAULT_FOCUS = new Vertex(0, 0, 0, 0.0f);
    public static final int CAMERA_DEFAULT_PROJECTION_TYPE = 1;
}
