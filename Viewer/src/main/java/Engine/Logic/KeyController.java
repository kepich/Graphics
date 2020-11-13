package Engine.Logic;

import Engine.Configurations;
import Engine.Logic.Actions.Action;
import Engine.Logic.Actions.Transformations.Transformation;
import Engine.Logic.Objects.Object;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;

public class KeyController {
    private static Long WINDOW;
    private Map<Integer, Action> keyCallbacks = new HashMap<>(0);
    private Vector<Object> objects;

    public KeyController(Long windowHandler, Vector<Object> objects) {
        WINDOW = windowHandler;
        this.objects = objects;

        this.initializeActions();
    }

    public void update(Object selectedObject) {
        for (int key : keyCallbacks.keySet())
            if (glfwGetKey(WINDOW, key) == 1)
                keyCallbacks.get(key).perform(selectedObject);
    }

    public void setKeyCallback(int glfwKey, Action action) {
        this.keyCallbacks.put(glfwKey, action);
    }

    private void initializeActions() {
        initializeRequiredActions();
    }

    private void initializeRequiredActions() {
        initializeMovementActions();
        initializeRotationActions();
        initializeResizeActions();
    }

    private void initializeMovementActions() {
        this.setKeyCallback(GLFW_KEY_RIGHT, (object) -> {
            Vector<Float> offset = new Vector<>();
            offset.add(Configurations.MOVEMENT_SPEED);
            offset.add(0f);
            offset.add(0f);

            Transformation.offset((Object) object, offset);
        });
        this.setKeyCallback(GLFW_KEY_LEFT, (object) -> {
            Vector<Float> offset = new Vector<>();
            offset.add(-Configurations.MOVEMENT_SPEED);
            offset.add(0f);
            offset.add(0f);

            Transformation.offset((Object) object, offset);
        });
        this.setKeyCallback(GLFW_KEY_UP, (object) -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(Configurations.MOVEMENT_SPEED);
            offset.add(0f);

            Transformation.offset((Object) object, offset);
        });
        this.setKeyCallback(GLFW_KEY_DOWN, (object) -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(-Configurations.MOVEMENT_SPEED);
            offset.add(0f);

            Transformation.offset((Object) object, offset);
        });
        this.setKeyCallback(GLFW_KEY_KP_ADD, (object) -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(0f);
            offset.add(Configurations.MOVEMENT_SPEED);

            Transformation.offset((Object) object, offset);
        });
        this.setKeyCallback(GLFW_KEY_KP_SUBTRACT, (object) -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(0f);
            offset.add(-Configurations.MOVEMENT_SPEED);

            Transformation.offset((Object) object, offset);
        });
    }

    private void initializeRotationActions() {
        this.setKeyCallback(GLFW_KEY_KP_4, (object) -> {
            Transformation.rotateY((Object) object, -Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_6, (object) -> {
            Transformation.rotateY((Object) object, Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_8, (object) -> {
            Transformation.rotateX((Object) object, -Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_5, (object) -> {
            Transformation.rotateX((Object) object, Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_7, (object) -> {
            Transformation.rotateZ((Object) object, Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_9, (object) -> {
            Transformation.rotateZ((Object) object, -Configurations.ROTATION_SPEED);
        });
    }

    private void initializeResizeActions() {
        this.setKeyCallback(GLFW_KEY_Q, (object) -> {
            Transformation.measureX((Object) object, 1 / Configurations.RESIZE_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_A, (object) -> {
            Transformation.measureX((Object) object, Configurations.RESIZE_SPEED);
        });

        this.setKeyCallback(GLFW_KEY_W, (object) -> {
            Transformation.measureY((Object) object, 1 / Configurations.RESIZE_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_S, (object) -> {
            Transformation.measureY((Object) object, Configurations.RESIZE_SPEED);
        });

        this.setKeyCallback(GLFW_KEY_E, (object) -> {
            Transformation.measureZ((Object) object, 1 / Configurations.RESIZE_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_D, (object) -> {
            Transformation.measureZ((Object) object, Configurations.RESIZE_SPEED);
        });

        this.setKeyCallback(GLFW_KEY_R, (object) -> {
            Transformation.measureComplex((Object) object, 1 / Configurations.RESIZE_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_F, (object) -> {
            Transformation.measureComplex((Object) object, Configurations.RESIZE_SPEED);
        });
    }
}
