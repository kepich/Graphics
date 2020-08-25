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
        Vertex f1 = new Vertex(100, 100, 0, 1);
        Vertex f2 = new Vertex(100, 500, 0, 1);
        Vertex f3 = new Vertex(500, 100, 0, 1);
        Vertex f4 = new Vertex(500, 500, 0, 1);

        this.objects.add(new Segment(f1, f2, Color.WHITE));
        this.objects.add(new Segment(f1, f3, Color.WHITE));
        this.objects.add(new Segment(f3, f4, Color.WHITE));
        this.objects.add(new Segment(f2, f4, Color.WHITE));

        Vertex b1 = new Vertex(100, 100, -400, 1);
        Vertex b2 = new Vertex(100, 500, -400, 1);
        Vertex b3 = new Vertex(500, 100, -400, 1);
        Vertex b4 = new Vertex(500, 500, -400, 1);

        this.objects.add(new Segment(b1, b2, Color.WHITE));
        this.objects.add(new Segment(b1, b3, Color.WHITE));
        this.objects.add(new Segment(b3, b4, Color.WHITE));
        this.objects.add(new Segment(b2, b4, Color.WHITE));

        this.objects.add(new Segment(f1, b1, Color.WHITE));
        this.objects.add(new Segment(f2, b2, Color.WHITE));
        this.objects.add(new Segment(f3, b3, Color.WHITE));
        this.objects.add(new Segment(f4, b4, Color.WHITE));
    }
}
