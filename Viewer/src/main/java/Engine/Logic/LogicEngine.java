package Engine.Logic;

import Engine.Configurations;
import Engine.Logic.Actions.Transformations.Transformation;
import Engine.Logic.Objects.Object;
import Engine.Logic.Objects.Parallelepiped;
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
        addObject(new Parallelepiped(0, 0, 0, 150, 150, 100, Color.WHITE));
        //addObject(new Parallelepiped(0, 0, 0, 150, 150, 100, Color.BLACK));
    }

    private void initializeActions(){
        keyController.setKeyCallback(GLFW_KEY_TAB, object -> nextSelectedObject() );

        keyController.setKeyCallback(GLFW_KEY_SPACE, object -> addForceToSelectedObject((Object) object) );
    }

    private void addForceToSelectedObject(Object object) {
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
