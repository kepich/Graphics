package Utils;

import Utils.Color;
import java.util.Vector;

public class Pixel {
    private Vector<Integer> position;
    private Color color;

    public Pixel(int xPos, int yPos, Color color){
        this.position = new Vector<>(0);
        this.position.add(xPos);
        this.position.add(yPos);
        this.color = color;
    }

    public Pixel(Vector<Integer> pos, Color color){
        this.position = pos;
        this.color = color;
    }

    public Pixel(int xPos, int yPos){
        this.position = new Vector<>(0);
        this.position.add(xPos);
        this.position.add(yPos);
    }

    public Vector<Integer> getPosition() {
        return position;
    }

    public Vector<Integer> getRelativePosition(int xCenter, int yCenter) {
        Vector<Integer> relPos = new Vector<>();
        relPos.add(this.position.elementAt(0) + xCenter);
        relPos.add(this.position.elementAt(1) + yCenter);

        return relPos;
    }

    public Color getColor() {
        return color;
    }

    public Color setColor(Color color) {
        return this.color = color;
    }
}
