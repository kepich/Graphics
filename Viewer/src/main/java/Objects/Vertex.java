package Objects;

import java.util.Vector;

public class Vertex {
    private float xPos;
    private float yPos;
    private float zPos;
    private float h = 1;

    private static float H_NORMALISATION = 1;

    public Vertex(int xPos, int yPos, int zPos, float h) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;

        this.h = h;
    }

    public void setPos(Vector<Float> cords){
        this.xPos = cords.elementAt(0);
        this.yPos = cords.elementAt(1);
        this.zPos = cords.elementAt(2);
    }

    public Vertex(float xPos, float yPos, float zPos, float h) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;

        this.h = h;
    }

    public Vertex(Vector<Float> cords) {
        this.xPos = cords.elementAt(0);
        this.yPos = cords.elementAt(1);
        this.zPos = cords.elementAt(2);
        this.h = cords.elementAt(3);
    }

    public Vector<Float> getCords(){
        Vector<Float> result = new Vector<>();
        result.add(xPos);
        result.add(yPos);
        result.add(zPos);
        result.add(h);

        return result;
    }

    public Vertex normalize(){
        this.xPos = this.xPos / this.h * H_NORMALISATION;
        this.yPos = this.yPos / this.h * H_NORMALISATION;
        this.zPos = this.zPos / this.h * H_NORMALISATION;
        this.h = H_NORMALISATION;

        return this;
    }

}
