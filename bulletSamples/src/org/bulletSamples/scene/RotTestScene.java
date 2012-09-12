package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Box;
import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;

//Scena s³u¿y³a do ustalenia jednostki prêdkoœci k¹towej.
public class RotTestScene extends BaseScene {
	protected CollisionShape box = null;
	protected Box cube = null;
	protected Sphere sphere = null;
	
	public RotTestScene(DynamicsWorld dw) {
		super(dw);
	}
	
	public void create()
	{
		Camera.active = new Camera(new Vector3(0,2,10), 0, 0);
		cube = new Box(1,1,1);
		sphere = new Sphere(1);
		box = dw.createShape(sphere, new Vector3(0, 2, 0), 1);
		box.setAngularVelocity(new Vector3(0,0,(float)Math.PI*2.0f));
		dw.setGravity(Vector3.zero());
	}
	
	public void render(GL10 gl)
	{
		box.getAngularKineticEnergy();
		box.render(gl);
	}
}