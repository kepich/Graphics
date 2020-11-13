package Engine.Logic;

import Engine.Configurations;
import Engine.Logic.Actions.Transformations.Transformation;
import Engine.Logic.Objects.*;
import Engine.Logic.Objects.Object;
import Utils.Color;

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
        this.selectedObjectIndex = 0;
    }

    public void update(){
        this.keyController.update(this.objects.elementAt(this.selectedObjectIndex));

        this.jumpMechanic();
    }

    public void addObject(Object object){
        this.objects.add(object);
    }

    private void initializeObjects(){
        //this.createCoordinateAxes();
        Parallelepiped p = new Parallelepiped(300, 300, 300, 50, 50, 50, Color.WHITE);
        Transformation.rotateY(p, 0.5f);
        addObject(p);
        p = new Parallelepiped(300, 300, 300, 50, 50, 50, Color.WHITE);
        Transformation.rotateZ(p, 0.5f);
        addObject(p);

        Tetraedr t = new Tetraedr(new Vertex[]{
                new Vertex(100, 100, 100, 1f),
                new Vertex(100, 300, 100, 1f),
                new Vertex(300, 200, 50, 1f),
                new Vertex(150, 150, 300, 1f),
        }, Color.WHITE);
        Transformation.rotateZ(t, 0.5f);
        addObject(t);

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
