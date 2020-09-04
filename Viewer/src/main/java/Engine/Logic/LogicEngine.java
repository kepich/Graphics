package Engine.Logic;

import Engine.Configurations;
import Engine.Logic.Actions.Transformations.Transformation;
import Engine.Logic.Objects.*;
import Engine.Logic.Objects.Object;
import Utils.Color;

import java.util.List;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;

public class LogicEngine {

    private Vector<Object> objects;
    private KeyController keyController;
    private int selectedObjectIndex;

    private float jumpForce;
    private boolean isJump = false;

    public LogicEngine(Vector<Object> objects, KeyController keyController){
        this.objects = objects;
        this.keyController = keyController;

        initializeObjects();
        initializeActions();
        this.selectedObjectIndex = 3;
    }

    public void update(){
        this.keyController.update(this.objects.elementAt(this.selectedObjectIndex));

        this.jumpMechanic();
    }

    public void addObject(Object object){
        this.objects.add(object);
    }

    private void initializeObjects(){
        this.createCoordinateAxes();
        addObject(new Parallelepiped(0, 0, 0, 150, 150, 100, Color.WHITE));

        Vector<Float[]> polyhedronCords = new Vector<>();
        polyhedronCords.add(new Float[]{100f, 100f, -100f, 1f});
        polyhedronCords.add(new Float[]{-100f, 100f, -100f, 1f});
        polyhedronCords.add(new Float[]{-100f, -100f, -100f, 1f});
        polyhedronCords.add(new Float[]{100f, -100f, -100f, 1f});
        polyhedronCords.add(new Float[]{0f, 0f, 0f, 1f});
        polyhedronCords.add(new Float[]{50f, 50f, 100f, 1f});
        polyhedronCords.add(new Float[]{-50f, 0f, 100f, 1f});
        polyhedronCords.add(new Float[]{50f, -50f, 100f, 1f});
        addObject(new Polyhedron(polyhedronCords, new boolean[][]{
                {false, true, false, true, true, false, false, false},
                {false, false, true, false, true, false, false, false},
                {false, false, false, true, true, false, false, false},
                {false, false, false, false, true, false, false, false},
                {false, false, false, false, false, true, true, true},
                {false, false, false, false, false, false, true, true},
                {false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false},
        }, Color.WHITE));
    }

    private void createCoordinateAxes(){
        Object axis = new Segment(
                new Vertex(0, 0, 0, 1),
                new Vertex(200, 0, 0, 1),
                Color.RED
        );
        addObject(axis);
        axis.setMutable(false);

        axis = new Segment(
                new Vertex(0, 0, 0, 1),
                new Vertex(0, 200, 0, 1),
                Color.GREEN
        );
        addObject(axis);
        axis.setMutable(false);

        axis = new Segment(
                new Vertex(0, 0, 0, 1),
                new Vertex(0, 0, 200, 1),
                Color.BLUE
        );
        addObject(axis);
        axis.setMutable(false);
    }

    private void initializeActions(){
        keyController.setKeyCallback(GLFW_KEY_TAB, object -> nextSelectedObject() );

        keyController.setKeyCallback(GLFW_KEY_SPACE, object -> addForceToSelectedObject() );
    }

    private void addForceToSelectedObject() {
        if (!this.isJump){
            this.jumpForce = Configurations.JUMP_BOOST;
            this.isJump = true;
        }
    }

    private void nextSelectedObject(){
        this.selectedObjectIndex = (this.selectedObjectIndex + 1) % this.objects.size();
    }

    // TODO: Lab 1: JUMP MECHANIC --------------------------------------------
    private void jumpMechanic() {
        if (this.isJump) {
            Vector<Float> offset = new Vector<>();
            offset.add(0f);
            offset.add(this.jumpForce);
            offset.add(0f);

            Transformation.offset(this.objects.elementAt(this.selectedObjectIndex), offset);

            if (this.jumpForce > -Configurations.JUMP_BOOST)
                this.jumpForce -= Configurations.GRAVITY_FORCE;
            else {
                this.jumpForce = 0;
                this.isJump = false;
            }
        }
    }


}
