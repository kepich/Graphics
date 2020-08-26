package Objects;

import Engine.Render.RenderEngine;

import java.util.Vector;

public abstract class Object {
    public abstract void draw(RenderEngine renderEngine);

    public abstract Vector<Vertex> getVertexes();
}
