package Engine.Render;

import Utils.Color;
import javafx.util.Pair;

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

    public Color getColor() {
        return color;
    }

    public Color setColor(Color color) {
        return this.color = color;
    }
}
