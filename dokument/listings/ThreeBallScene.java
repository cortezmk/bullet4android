package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;
import org.bulletSamples.geometry.*;
import org.bulletSamples.physics.*;

public class ThreeBallScene extends BaseScene {

	public ThreeBallScene(DynamicsWorld dw) { super(dw); }
	
	protected CollisionShape[] spheres;
	protected Sphere sphere;
	
	public void create()
	{
		Camera.active = new Camera(new Vector3(0, 50, 0), 0, 270);
		sphere = new Sphere(.5f);
		spheres = new CollisionShape[] {
			dw.createShape(sphere, new Vector3(0f,.5f,-10f), 10),
			dw.createShape(sphere, new Vector3((float)Math.pow(3, .5f) * .5f * 10,.5f,5), 10),
			dw.createShape(sphere, new Vector3(-(float)Math.pow(3, .5f) * .5f * 10,.5f,5), 10)
		};
		spheres[0].setLinearVelocity(new Vector3(0,0,10f));
		spheres[1].setLinearVelocity(new Vector3(-(float)Math.pow(3, .5f) * .5f * 10,0,-5f));
		spheres[2].setLinearVelocity(new Vector3((float)Math.pow(3, .5f) * .5f * 10,0,-5f));
		for(int i = 0; i < spheres.length; i++) spheres[i].setRestitution(1f);
	}
	
	public void render(GL10 gl)
	{
		for(int i = 0; i < spheres.length; i++) spheres[i].render(gl);
	}
}
