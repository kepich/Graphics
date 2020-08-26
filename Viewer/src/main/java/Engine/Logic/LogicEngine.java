package Engine.Logic;

import Objects.Object;
import Objects.Parallelepiped;
import Objects.Segment.Segment;
import Objects.Vertex;
import Tranformation.Transform;
import Utils.Color;

import java.util.List;
import java.util.Vector;

public class LogicEngine {

    private List<Object> objects;

    public LogicEngine(List<Object> objects){
        this.objects = objects;

        initializeObjects();
    }

    public void update(){
        Vector<Float> offset = new Vector<>();
        offset.add(0.9f);
        offset.add(0.2f);
        offset.add(0.0f);

        for(Object obj: this.objects)
            Transform.rotateZ(obj, 0.01f);

        for(Object obj: this.objects)
            Transform.rotateX(obj, 0.1f);

        for(Object obj: this.objects)
            Transform.offset(obj, offset);
    }

    private void initializeObjects(){
        Parallelepiped cube = new Parallelepiped(0, 0, 0, 150, 150, 100, Color.WHITE);

        this.objects.add(cube);

        for(Object obj: this.objects)
            Transform.rotateX(obj, 1f);
    }
}
