package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;
import org.bulletSamples.geometry.Box;
import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;

public class RotationTestScene extends BaseScene {
	protected CollisionShape ball = null;
	protected Sphere sphere = null;
	protected Box box = null;
	protected CollisionShape floor = null;
	
	public RotationTestScene(DynamicsWorld dw) {
		super(dw);
		//Camera.active = new Camera(new Vector3(0,0,10),0,10);
		
		// TODO Auto-generated constructor stub
	}
	
	public void create()
	{
		Camera.active = new Camera(new Vector3(0,10,40), 0, 0);
		sphere = new Sphere(1f);
		ball = dw.createShape(sphere ,new Vector3(0, 40, 0), 1);
		ball.setRestitution(.7f);
		ball.setFriction(10f);
		ball.setAngularVelocity(new Vector3(0,0,10));
		box = new Box(10, 1, 10);
		floor = dw.createShape(box, new Vector3(0,1,0), 0);
		floor.setRestitution(.7f);
		floor.setFriction(10f);
	}
	
	public void render(GL10 gl)
	{
		ball.render(gl);
		floor.render(gl);
	}
}
