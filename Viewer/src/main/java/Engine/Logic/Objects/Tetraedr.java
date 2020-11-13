package Engine.Logic.Objects;

import Utils.Color;
import Utils.Pixel;

import java.util.Arrays;
import java.util.Vector;

public class Tetraedr extends Object {
    private Segment ds1;
    private Segment ds2;
    private Segment ds3;

    private Segment us1;
    private Segment us2;
    private Segment us3;

    private Vector<Vertex> vertexes;

    public Tetraedr(Vertex[] vertexes, Color color) {
        this.color = color;

        this.polygons = new Polygon[]{
                new Polygon(new Vertex[]{
                        vertexes[0],
                        vertexes[1],
                        vertexes[2]

                }, Color.BLACK),
                new Polygon(new Vertex[]{
                        vertexes[0],
                        vertexes[1],
                        vertexes[3]

                }, Color.BLUE),
                new Polygon(new Vertex[]{
                        vertexes[0],
                        vertexes[2],
                        vertexes[3]

                }, Color.WHITE),
                new Polygon(new Vertex[]{
                        vertexes[1],
                        vertexes[2],
                        vertexes[3]

                }, Color.RED)
        };

        this.ds1 = new Segment(vertexes[0], vertexes[1], color);
        this.ds2 = new Segment(vertexes[0], vertexes[2], color);
        this.ds3 = new Segment(vertexes[1], vertexes[2], color);
        this.us1 = new Segment(vertexes[0], vertexes[3], color);
        this.us2 = new Segment(vertexes[1], vertexes[3], color);
        this.us3 = new Segment(vertexes[2], vertexes[3], color);

        this.vertexes = new Vector<Vertex>(Arrays.asList(vertexes));
    }

    @Override
    public Vector<Pixel> draw() {
        Vector<Pixel> pixels = new Vector<>();
        pixels.addAll(this.ds1.draw());
        pixels.addAll(this.ds2.draw());
        pixels.addAll(this.ds3.draw());
        pixels.addAll(this.us1.draw());
        pixels.addAll(this.us2.draw());
        pixels.addAll(this.us3.draw());
        return pixels;
    }

    @Override
    public Vector<Vertex> getVertexes() {
        return this.vertexes;
    }

    @Override
    public Polygon[] getPolygons() {
        return this.polygons;
    }
}
