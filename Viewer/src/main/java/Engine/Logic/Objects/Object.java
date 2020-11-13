package Engine.Logic.Objects;

import Utils.Color;
import Utils.Pixel;

import java.util.Vector;

public abstract class Object {
    protected boolean isMutable = true;
    protected Polygon[] polygons = null;
    protected Color color;

    public abstract Vector<Pixel> draw();

    public abstract Vector<Vertex> getVertexes();

    public void setMutable(boolean mutable) {
        isMutable = mutable;
    }

    public boolean isMutable() {
        return isMutable;
    }

    public abstract Polygon[] getPolygons();

    public Color getColor(){
        return this.color;
    }
}
