package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.Logger;
import org.bulletSamples.physics.*;
import org.bulletSamples.geometry.*;

public class InclinedPlaneScene extends BaseScene {

	private Box floorShape;
	private Box boxShape;
	private Sphere sphereShape;
	
	private CollisionShape sphere, box ,floor;
	
	public InclinedPlaneScene(DynamicsWorld dw) {
		super(dw);
	}
	
	public void create()
	{
		float width = 30;
		Camera.active = new Camera(new Vector3(0,width*5,0), 0, 0);
		floorShape = new Box(width, .5f, 10);
		sphereShape = new Sphere(.5f);
		boxShape = new Box(.5f, .5f, .5f);
		
		floor = dw.createShape(floorShape, new Vector3(0,width,0), 0);
		floor.setRotation(new Quaternion(new Vector3(0,0,1), (float)Math.PI/4.0f));
		floor.setFriction(.1f);
		float sqr2 = (float)Math.pow(2, .5f);
		float pos = width/sqr2/2 -.5f;
		
		box = dw.createShape(boxShape, new Vector3(pos - 1.0f/sqr2,1+width+pos + 1.0f/sqr2,2), 1);
		box.setRotation(new Quaternion(new Vector3(0,0,1), -(float)Math.PI/4.0f));
		box.setFriction(2.0f/5.0f);
		
		sphere = dw.createShape(sphereShape, new Vector3(pos - 1.0f/sqr2,1+width+pos + 1.0f/sqr2,-2), 1);
		sphere.setFriction(2.0f/5.0f);
		
		Logger.setLogFile("results.log");
	}
	
	public void render(GL10 gl)
	{
		floor.render(gl);
		box.render(gl);
		sphere.render(gl);
		Logger.write(box.getTranslation().y + "," + box.getLinearVelocity().length() + "," + sphere.getTranslation().y + "," + sphere.getLinearVelocity().length());
	}
}
