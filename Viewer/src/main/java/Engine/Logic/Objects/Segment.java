package Engine.Logic.Objects;

import Engine.Adapters.SegmentAdapter;
import Utils.Color;
import Utils.Pixel;

import java.util.Vector;

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
    public Vector<Pixel> draw() {
        return SegmentAdapter.draw(this);
    }

    @Override
    public Vector<Vertex> getVertexes() {
        Vector<Vertex> result = new Vector<>();
        result.add(vertex1);
        result.add(vertex2);

        return result;
    }

    @Override
    public Polygon[] getPolygons() {
        return new Polygon[0];
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public Color getColor() {
        return color;
    }
}
