package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				//triangle #1
				-0.5f, 0.5f, 0f,  //0
				-0.5f, -0.5f, 0f, //1
				0.5f, -0.5f, 0f,  //2
				0.5f, 0.5f, 0f}; //3
		
		int[] indices = {
			0,1,3, //Top Left
			3,1,2  //Bottom Right
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while (!Display.isCloseRequested()) {
			renderer.prepare();
			renderer.render(model);
			
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
