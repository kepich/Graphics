package Objects.Segment;

import Engine.Render.RenderEngine;
import Objects.Object;
import Objects.Vertex;
import Utils.Color;

public class Segment extends Object {
    private Vertex vertex1;
    private Vertex vertex2;

    private Color color;

    public Segment(Vertex vertex1, Vertex vertex2, Color color){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.color = color;
    }

    @Override
    public void draw(RenderEngine renderEngine) {
        SegmentDrawer.draw(this, renderEngine);
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }
}
