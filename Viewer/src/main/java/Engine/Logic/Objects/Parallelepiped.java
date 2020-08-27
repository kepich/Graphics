package Engine.Logic.Objects;

import Engine.Render.Pixel;
import Utils.Color;

import java.util.Vector;

public class Parallelepiped extends Object {
    private Segment fs1;
    private Segment fs2;
    private Segment fs3;
    private Segment fs4;

    private Segment bs1;
    private Segment bs2;
    private Segment bs3;
    private Segment bs4;

    private Segment ts1;
    private Segment ts2;
    private Segment ts3;
    private Segment ts4;

    private Vector<Vertex> vertexes = new Vector<>();

    public Parallelepiped(float xPos, float yPos, float zPos, float xSize, float ySize, float zSize, Color color) {
        Vertex f1 = new Vertex(xPos - xSize / 2, yPos - ySize / 2, zPos + zSize / 2, 1);
        Vertex f2 = new Vertex(xPos - xSize / 2, yPos + ySize / 2, zPos + zSize / 2, 1);
        Vertex f3 = new Vertex(xPos + xSize / 2, yPos - ySize / 2, zPos + zSize / 2, 1);
        Vertex f4 = new Vertex(xPos + xSize / 2, yPos + ySize / 2, zPos + zSize / 2, 1);

        Vertex b1 = new Vertex(xPos - xSize / 2, yPos - ySize / 2, zPos - zSize / 2, 1);
        Vertex b2 = new Vertex(xPos - xSize / 2, yPos + ySize / 2, zPos - zSize / 2, 1);
        Vertex b3 = new Vertex(xPos + xSize / 2, yPos - ySize / 2, zPos - zSize / 2, 1);
        Vertex b4 = new Vertex(xPos + xSize / 2, yPos + ySize / 2, zPos - zSize / 2, 1);

        this.vertexes.add(f1);
        this.vertexes.add(f2);
        this.vertexes.add(f3);
        this.vertexes.add(f4);

        this.vertexes.add(b1);
        this.vertexes.add(b2);
        this.vertexes.add(b3);
        this.vertexes.add(b4);

        this.fs1 = new Segment(f1, f2, color);
        this.fs2 = new Segment(f1, f3, color);
        this.fs3 = new Segment(f3, f4, color);
        this.fs4 = new Segment(f2, f4, color);

        this.bs1 = new Segment(b1, b2, color);
        this.bs2 = new Segment(b1, b3, color);
        this.bs3 = new Segment(b3, b4, color);
        this.bs4 = new Segment(b2, b4, color);

        this.ts1 = new Segment(f1, b1, color);
        this.ts2 = new Segment(f2, b2, color);
        this.ts3 = new Segment(f3, b3, color);
        this.ts4 = new Segment(f4, b4, color);
    }

    @Override
    public Vector<Pixel> draw() {
        Vector<Pixel> pixels = new Vector<>();
        pixels.addAll(this.fs1.draw());
        pixels.addAll(this.fs2.draw());
        pixels.addAll(this.fs3.draw());
        pixels.addAll(this.fs4.draw());
        pixels.addAll(this.bs1.draw());
        pixels.addAll(this.bs2.draw());
        pixels.addAll(this.bs3.draw());
        pixels.addAll(this.bs4.draw());
        pixels.addAll(this.ts1.draw());
        pixels.addAll(this.ts2.draw());
        pixels.addAll(this.ts3.draw());
        pixels.addAll(this.ts4.draw());
        return pixels;
    }

    @Override
    public Vector<Vertex> getVertexes() {
        return this.vertexes;
    }
}
