package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Shape;
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
		Camera.active = new Camera(new Vector3(0,0,0),0,10);
		
		// TODO Auto-generated constructor stub
	}
	
	public void create()
	{
		box = dw.createShape(Shape.Sphere ,new Vector3(0, 1, -20), 1);
		box2 = dw.createShape(Shape.Sphere, new Vector3(.5f, 7, -20.1f), 1);
		sphere = new Sphere(1f);;
		sphere2 = new Sphere(1f);
	}
	
	public void render(GL10 gl)
	{
		sphere.applyTransformAndRender(box, gl);
		sphere2.applyTransformAndRender(box2, gl);
	}
}
