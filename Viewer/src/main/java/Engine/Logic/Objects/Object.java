package Engine.Logic.Objects;

import Engine.Render.Pixel;

import java.util.Vector;

public abstract class Object {
    private boolean isMutable = true;
    public abstract Vector<Pixel> draw();

    public abstract Vector<Vertex> getVertexes();

    public void setMutable(boolean mutable) {
        isMutable = mutable;
    }

    public boolean isMutable() {
        return isMutable;
    }
}
