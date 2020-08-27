package Engine.Logic;

import Engine.Logic.Objects.Object;
import Engine.Logic.Objects.Parallelepiped;
import Utils.Color;

import java.util.List;

public class LogicEngine {

    private List<Object> objects;
    private KeyController keyController;

    public LogicEngine(List<Object> objects, KeyController keyController){
        this.objects = objects;
        this.keyController = keyController;

        initializeObjects();
    }

    public void update(){
        this.keyController.update();
        /*
        Logic
         */
    }

    public void addObject(Object object){
        this.objects.add(object);
    }

    private void initializeObjects(){
        addObject(new Parallelepiped(0, 0, 0, 150, 150, 100, Color.WHITE));
    }
}
