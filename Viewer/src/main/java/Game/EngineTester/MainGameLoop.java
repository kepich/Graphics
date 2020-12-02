package Game.EngineTester;


import Game.Entitiy.Camera;
import Game.Entitiy.Entity;
import Game.Entitiy.Light;
import Game.OBJLoader;
import Game.RenerEngine.*;
import Game.Model.RawModel;
import Game.Model.TexturedModel;
import Game.RenerEngine.Terrain.Plane;
import Game.RenerEngine.Texture.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();

        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random();

        RawModel model = OBJLoader.loadObjModel("tree", loader);
        RawModel house = OBJLoader.loadObjModel("cottage", loader);
        RawModel tower = OBJLoader.loadObjModel("tower", loader);
        RawModel building = OBJLoader.loadObjModel("building", loader);
        RawModel wolf = OBJLoader.loadObjModel("wolf", loader);
        RawModel dennis = OBJLoader.loadObjModel("dennis", loader);
        RawModel museim = OBJLoader.loadObjModel("museim", loader);

        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("box")));
        TexturedModel cottageModel = new TexturedModel(house,new ModelTexture(loader.loadTexture("box")));
        TexturedModel towerModel = new TexturedModel(tower,new ModelTexture(loader.loadTexture("box")));
        TexturedModel buildingModel = new TexturedModel(building,new ModelTexture(loader.loadTexture("box")));
        TexturedModel wolfModel = new TexturedModel(wolf,new ModelTexture(loader.loadTexture("box")));
        TexturedModel dennisModel = new TexturedModel(dennis,new ModelTexture(loader.loadTexture("box")));
        TexturedModel museimModel = new TexturedModel(museim,new ModelTexture(loader.loadTexture("stone")));

        entities.add(new Entity(staticModel, new Vector3f(60f,5,-250),0,0,0,7));
        entities.add(new Entity(cottageModel, new Vector3f(60f,5,-300),0,0,0,0.6f));
        entities.add(new Entity(towerModel, new Vector3f(60f,5,-350),0,0,0,3));
        entities.add(new Entity(buildingModel, new Vector3f(100f,5,-250),0,0,0,0.8f));
        entities.add(new Entity(wolfModel, new Vector3f(100f,5,-300),0,0,0,25));
        entities.add(new Entity(dennisModel, new Vector3f(100f,5,-350),0,0,0,5));
        entities.add(new Entity(museimModel, new Vector3f(80f,0,-300),0,0,0,10));

        Light light = new Light(new Vector3f(80,0,-250),new Vector3f(1,1,1), new Vector3f(0.5f,0f,0f));

        Plane plane = new Plane(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
        Plane plane2 = new Plane(1,0,loader,new ModelTexture(loader.loadTexture("grass")));

        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()){
            camera.move();

            renderer.processTerrain(plane);
            renderer.processTerrain(plane2);

            for(Entity entity:entities){
                //entity.increaseRotation(0,0.2f,0f);
                renderer.processEntity(entity);
            }

            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }

}