package Objects;

import Utils.Color;

public class Line extends Object {
    private Vertex vertex1;
    private Vertex vertex2;

    private Color color;

    public Line(Vertex vertex1, Vertex vertex2, Color color){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.color = color;
    }

    @Override
    public void draw(int[] buffer) {

    }
}
