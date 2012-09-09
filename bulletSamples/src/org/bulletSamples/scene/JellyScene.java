package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.Accelerometer;
import org.bulletSamples.geometry.*;
import org.bulletSamples.physics.*;

public class JellyScene extends BaseScene {
	public JellyScene(DynamicsWorld dw) { super(dw); }
	protected Sphere sphere = new Sphere(.5f);
	protected CollisionShape[] ball;
	protected Spring spring;
	protected Camera camera;
	public void create()
	{
		enableObjectDrag = true;
		camera = new Camera(new Vector3(0,0,30),0,0);
		Camera.active = camera;
		ball = new CollisionShape[]
		{
			dw.createShape(sphere, new Vector3(0,3,0), 0),
			dw.createShape(sphere, new Vector3(0,7,0), 1)
		};
		spring = new Spring(dw, ball[0], ball[1], new Vector3(0,0,0), new Vector3(0,0,0), true);
		spring.setLinerUpperLimit(new Vector3(5,10,0));
		spring.setLinerLowerLimit(new Vector3(-5,-10,0));
		spring.setAnglarLowerLimit(new Vector3(0,0,-1.5f));
		spring.setAnglarUpperLimit(new Vector3(0,0,1.5f));
		spring.setupDof(Dof.translateX, 39.478f, .1f);
		spring.setupDof(Dof.translateY, 39.478f, .1f);
		spring.setEquilibrumPoint();
	}
	
	public void render(GL10 gl)
	{
		//dw.setGravity(Accelerometer.gravity);
		for(int i = 0; i < ball.length; i++) ball[i].render(gl);
		dw.drawDebug(gl);
	}
}
