package Engine.Logic.Objects;

import Engine.Render.Pixel;

import java.util.Vector;

public abstract class Object {
    public abstract Vector<Pixel> draw();

    public abstract Vector<Vertex> getVertexes();
}
