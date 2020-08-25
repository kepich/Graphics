package Objects;

import java.math.BigDecimal;
import java.util.Vector;

public class Vertex {
    private int xPos;
    private int yPos;
    private int zPos;
    private float h = 1;

    private static float H_NORMALISATION = 1;

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

    public Vertex normalize(){
        this.xPos = BigDecimal.valueOf(this.xPos / this.h * H_NORMALISATION).intValue();
        this.yPos = BigDecimal.valueOf(this.yPos / this.h * H_NORMALISATION).intValue();
        this.zPos = BigDecimal.valueOf(this.zPos / this.h * H_NORMALISATION).intValue();
        this.h = H_NORMALISATION;

        return this;
    }

}
