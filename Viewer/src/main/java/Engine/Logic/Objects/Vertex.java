package Engine.Logic.Objects;

import Engine.Render.Camera.Camera;
import Utils.Pixel;

import java.util.Vector;

import static Engine.Configurations.H_NORMALISATION;

public class Vertex extends Object {
    private float xPos;
    private float yPos;
    private float zPos;
    private float h;

    public Vertex(int xPos, int yPos, int zPos, float h) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;

        this.h = h;
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

    public void setPos(Vector<Float> cords){
        this.xPos = cords.elementAt(0);
        this.yPos = cords.elementAt(1);
        this.zPos = cords.elementAt(2);
    }

    public Vector<Float> getCords(){
        Vector<Float> result = new Vector<>();
        //Camera cam = Camera.getCamera();
        //Vertex v = cam.getPointProjection(this);
        result.add(this.xPos);
        result.add(this.yPos);
        result.add(this.zPos);
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

    @Override
    public Vector<Pixel> draw() {
        return new Vector<>();
    }

    @Override
    public Vector<Vertex> getVertexes() {
        Vector<Vertex> res = new Vector<>();
        res.add(this);

        return res;
    }

    @Override
    public Polygon[] getPolygons() {
        return null;
    }

    @Override
    public String toString() {
        return xPos + " " +  yPos + " " + zPos;
    }

    public Vector<Float> getRawCords() {
        Vector<Float> result = new Vector<>();
        result.add(xPos);
        result.add(yPos);
        result.add(zPos);
        result.add(h);

        return result;
    }
}
