package Engine.Logic;

import Objects.Object;
import Objects.Segment.Segment;
import Objects.Vertex;
import Utils.Color;

import java.util.List;

public class LogicEngine {

    private List<Object> objects;

    public LogicEngine(List<Object> objects){
        this.objects = objects;

        initializeObjects();
    }

    public void update(){

    }

    private void initializeObjects(){
        Vertex v1 = new Vertex(0, 0, 0, 1);
        Vertex v2 = new Vertex(5, 0, 0, 1);
        Vertex v3 = new Vertex(5, 5, 0, 1);
        Vertex v4 = new Vertex(0, 5, 0, 1);

        Color color = Color.WHITE;

        this.objects.add(new Segment(v1, v2, color));
    }
}
