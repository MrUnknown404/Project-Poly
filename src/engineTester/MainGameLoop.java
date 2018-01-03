package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		RawModel model = OBJLoader.loadObjModel("Stall", loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("stallTexture")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0.0f, -2.5f, -20f), 0.0f, 0.0f, 0.0f, 1.0f); //X Y Z RX RY RZ Scale
		
		Camera camera = new Camera();
		
		while (!Display.isCloseRequested()) {
			//entity.increasePosition(0.0f, 0.0f, -0.02f); //XYZ
			//entity.increaseRotation(0.0f, 0.65f, 0.65f); //XYZ
			entity.increaseRotation(0.0f, 0.65f, 0.0f); //XYZ
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity,shader);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
