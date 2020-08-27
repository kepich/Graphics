package Engine.Logic;

import Engine.Configurations;
import Engine.Logic.Objects.Object;
import Engine.Logic.Actions.Action;
import Engine.Logic.Actions.Transformations.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;

public class KeyController {
    private static Long WINDOW;
    private Map<Integer, Action> keyCallbacks = new HashMap<>(0);
    private ArrayList<Object> objects;

    public KeyController(Long windowHandler, ArrayList<Object> objects) {
        WINDOW = windowHandler;
        this.objects = objects;

        this.initializeActions();
    }

    public void update() {
        for (int key : keyCallbacks.keySet())
            if (glfwGetKey(WINDOW, key) == 1)
                keyCallbacks.get(key).perform();
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
    }

    private void initializeMovementActions() {
        this.setKeyCallback(GLFW_KEY_RIGHT, () -> {
            Vector<Float> offset = new Vector<>();
            offset.add(Configurations.MOVEMENT_SPEED);
            offset.add(0f);
            offset.add(0f);

            for (Object obj : this.objects)
                Transformation.offset(obj, offset);
        });
        this.setKeyCallback(GLFW_KEY_LEFT, () -> {
            Vector<Float> offset = new Vector<>();
            offset.add(-Configurations.MOVEMENT_SPEED);
            offset.add(0f);
            offset.add(0f);

            for (Object obj : this.objects)
                Transformation.offset(obj, offset);
        });
        this.setKeyCallback(GLFW_KEY_UP, () -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(Configurations.MOVEMENT_SPEED);
            offset.add(0f);

            for (Object obj : this.objects)
                Transformation.offset(obj, offset);
        });
        this.setKeyCallback(GLFW_KEY_DOWN, () -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(-Configurations.MOVEMENT_SPEED);
            offset.add(0f);

            for (Object obj : this.objects)
                Transformation.offset(obj, offset);
        });
        this.setKeyCallback(GLFW_KEY_KP_ADD, () -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(0f);
            offset.add(Configurations.MOVEMENT_SPEED);

            for (Object obj : this.objects)
                Transformation.offset(obj, offset);
        });
        this.setKeyCallback(GLFW_KEY_KP_SUBTRACT, () -> {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(0f);
            offset.add(-Configurations.MOVEMENT_SPEED);

            for (Object obj : this.objects)
                Transformation.offset(obj, offset);
        });
    }

    private void initializeRotationActions() {
        this.setKeyCallback(GLFW_KEY_KP_4, () -> {
            for (Object obj : this.objects)
                Transformation.rotateY(obj, -Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_6, () -> {
            for (Object obj : this.objects)
                Transformation.rotateY(obj, Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_8, () -> {
            for (Object obj : this.objects)
                Transformation.rotateX(obj, -Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_2, () -> {
            for (Object obj : this.objects)
                Transformation.rotateX(obj, Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_7, () -> {
            for (Object obj : this.objects)
                Transformation.rotateZ(obj, Configurations.ROTATION_SPEED);
        });
        this.setKeyCallback(GLFW_KEY_KP_9, () -> {
            for (Object obj : this.objects)
                Transformation.rotateZ(obj, -Configurations.ROTATION_SPEED);
        });
    }
}
