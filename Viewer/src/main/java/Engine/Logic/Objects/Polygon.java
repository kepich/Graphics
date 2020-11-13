package Engine.Logic.Objects;

import Utils.Color;
import Utils.Lup;
import Utils.Pixel;

import java.util.Arrays;
import java.util.Vector;

public class Polygon extends Object{
    private Vertex[] vertices;

    public Polygon(Vertex[] vertices, Color color){
        this.vertices = vertices;
        this.polygons = new Polygon[] { this };
        this.color = color;
    }

    @Override
    public Vector<Pixel> draw() {
        return new Vector<>(0);
    }

    @Override
    public Vector<Vertex> getVertexes() {
        return new Vector<>(Arrays.asList(vertices));
    }

    public Vector<Segment> getSegments(){
        Vector<Segment> res = new Vector<>();
        for(int i = 0; i < this.vertices.length; i++){
            res.add(new Segment(this.vertices[i], this.vertices[(i + 1) % this.vertices.length], null));
        }
        return res;
    }

    @Override
    public Polygon[] getPolygons() {
        return new Polygon[] {this};
    }

    public double[] getPlaneCords(){
        double matrix[][] = {
                {vertices[0].getCords().get(0), vertices[0].getCords().get(1), vertices[0].getCords().get(2)},
                {vertices[1].getCords().get(0), vertices[1].getCords().get(1), vertices[1].getCords().get(2)},
                {vertices[2].getCords().get(0), vertices[2].getCords().get(1), vertices[2].getCords().get(2)},
        };

        double[] v1 = {
                matrix[1][0] - matrix[0][0],
                matrix[1][1] - matrix[0][1],
                matrix[1][2] - matrix[0][2]
        };
        double[] v2 = {
                matrix[2][0] - matrix[0][0],
                matrix[2][1] - matrix[0][1],
                matrix[2][2] - matrix[0][2]
        };

        double[] result = {
                v1[1] * v2[2] - v1[2] * v2[1],
                -(v1[0] * v2[2] - v1[2] * v2[0]),
                v1[0] * v2[1] - v1[1] * v2[0],
        };
        double d = -(result[0] * matrix[0][0] + result[1] * matrix[0][1] + result[2] * matrix[0][2]);
        return new double[]{result[0], result[1], result[2], d};
    }

    @Override
    public String toString() {
        return Arrays.toString(vertices);
    }
}
