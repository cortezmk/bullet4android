package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;

public class SimpleSphereScene extends BaseScene {
	protected CollisionShape box = null;
	protected CollisionShape box2 = null;
	protected Sphere sphere = null;
	protected Sphere sphere2 = null;
	
	public SimpleSphereScene(DynamicsWorld dw) {
		super(dw);
		//Camera.active = new Camera(new Vector3(0,0,10),0,10);
		
		// TODO Auto-generated constructor stub
	}
	
	public void create()
	{
		sphere = new Sphere(1f);
		sphere2 = new Sphere(1f);
		box = dw.createShape(sphere ,new Vector3(0, 1, -20), 1);
		box2 = dw.createShape(sphere2, new Vector3(.5f, 7, -20.1f), 1);
	}
	
	public void render(GL10 gl)
	{
		box.render(gl);
		box2.render(gl);
	}
}
