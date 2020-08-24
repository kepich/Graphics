package Objects;

import java.util.Vector;

public class Vertex {
    private int xPos;
    private int yPos;
    private int zPos;
    private float h = 1;

    public Vertex(int xPos, int yPos, int zPos, float h) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;

        this.h = h;
    }

    public Vertex(Vector<Float> cords) {
        this.xPos = cords.elementAt(0).intValue();
        this.yPos = cords.elementAt(1).intValue();
        this.zPos = cords.elementAt(2).intValue();
        this.h = cords.elementAt(3);
    }

    public Vector<Float> getCords(){
        Vector<Float> result = new Vector<>();
        result.add((float) xPos);
        result.add((float) yPos);
        result.add((float) zPos);
        result.add(h);

        return result;
    }


}
