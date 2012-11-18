package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.Accelerometer;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;
import org.bulletSamples.physics.UniversalConstraint;

public class NewtonPendulumScene extends BaseScene {
	public NewtonPendulumScene(DynamicsWorld dw) { super(dw); }

	protected CollisionShape[] spheres;
	protected UniversalConstraint[] constraints;
	protected Sphere sphere;
	
	public void create()
	{
		enableObjectDrag = true;
		sphere = new Sphere(.5f);
		spheres = new CollisionShape[] {
			dw.createShape(sphere, new Vector3(0f,7,-20), 0),
			dw.createShape(sphere, new Vector3(0f,2,-20), 10),
			dw.createShape(sphere, new Vector3(1f,7,-20), 0),
			dw.createShape(sphere, new Vector3(1f,2,-20), 10),
			dw.createShape(sphere, new Vector3(-1f,7,-20), 0),
			dw.createShape(sphere, new Vector3(-1f,2,-20), 10),
			dw.createShape(sphere, new Vector3(2f,7,-20), 0),
			dw.createShape(sphere, new Vector3(2f,2f,-20), 10)
		};
		for(int i = 0; i < spheres.length; i++) spheres[i].setRestitution(.95f);
		//boxes[5].applyCentralForce(new Vector3(-2000,0,0));
		constraints = new UniversalConstraint[spheres.length/2];
		for(int i = 0; i < spheres.length-1; i+=2)
		{
			UniversalConstraint uc = new UniversalConstraint(dw, spheres[i], spheres[i+1], spheres[i].getTranslation(), new Vector3(1,0,0),new Vector3(0,0,1));
	   		uc.setLowerLimit((float)(-Math.PI / 2.0), (float)(-Math.PI / 2.0));
	   		uc.setUpperLimit((float)(Math.PI / 2.0), (float)(Math.PI / 2.0));
			constraints[i/2] = uc;
		}
	}
	
	public void render(GL10 gl)
	{
		//dw.setGravity(Accelerometer.gravity);
		for(int i = 0; i < spheres.length; i++)
		{
			spheres[i].render(gl);
		}
	}
}
