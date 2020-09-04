package Engine.Logic.Objects;

import Engine.Render.Pixel;
import Utils.Color;

import java.util.Vector;

public class Polyhedron extends Object {
    private Vector<Vertex> vertices;
    private Vector<Segment> segments;

    public Polyhedron(Vector<Float[]> cords, boolean[][] incidentMatrix, Color color){
        this.vertices = new Vector<>();
        for(Float[] cord: cords)
            this.vertices.add(new Vertex(cord[0], cord[1], cord[2], cord[3]));

        this.segments = new Vector<>();
        for (int i = 0; i < this.vertices.size(); i++)
            for(int j = i + 1; j < this.vertices.size(); j++)
                if (incidentMatrix[i][j])
                    this.segments.add(new Segment(this.vertices.elementAt(i), this.vertices.elementAt(j), color));
    }

    @Override
    public Vector<Pixel> draw() {
        Vector<Pixel> result = new Vector<>();
        for (Segment segment: this.segments)
            result.addAll(segment.draw());

        return result;
    }

    @Override
    public Vector<Vertex> getVertexes() {
        return this.vertices;
    }
}
