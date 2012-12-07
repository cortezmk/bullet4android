package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.Logger;
import org.bulletSamples.geometry.*;
import org.bulletSamples.physics.*;

public class ElasticBodyScene extends BaseScene {
	public ElasticBodyScene(DynamicsWorld dw) { super(dw); }
	protected Sphere sphere = new Sphere(.5f);
	protected CollisionShape[] ball;
	protected Spring[] springs;
	protected Camera camera;

	private int count = 0; 
	public void create()
	{
		sphere.setColor(1, 0, 0, 1);
		enableObjectDrag = true;
		camera = new Camera(new Vector3(0,10,30),0,0);
		Camera.active = camera;
		ball = new CollisionShape[]
		{
			dw.createShape(sphere, new Vector3(0,3,0), 0),
			dw.createShape(sphere, new Vector3(0,7,0), 1),
			dw.createShape(sphere, new Vector3(0,11,0), 1),
			dw.createShape(sphere, new Vector3(0,15,0), 1),
			dw.createShape(sphere, new Vector3(2,3,0), 0),
			dw.createShape(sphere, new Vector3(2,7,0), 1),
			dw.createShape(sphere, new Vector3(2,11,0), 1),
			dw.createShape(sphere, new Vector3(2,15,0), 1),
			dw.createShape(sphere, new Vector3(-2,3,0), 0),
			dw.createShape(sphere, new Vector3(-2,7,0), 1),
			dw.createShape(sphere, new Vector3(-2,11,0), 1),
			dw.createShape(sphere, new Vector3(-2,15,0), 1),
		};
		springs = new Spring[]
		{
			new Spring(dw, ball[4], ball[5], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[5], ball[6], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[6], ball[7], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[0], ball[1], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[1], ball[2], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[2], ball[3], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[8], ball[9], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[9], ball[10], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[10], ball[11], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[11], ball[3], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[3], ball[7], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[10], ball[2], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[2], ball[6], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[9], ball[1], new Vector3(0,0,0), new Vector3(0,0,0), true),
			new Spring(dw, ball[1], ball[5], new Vector3(0,0,0), new Vector3(0,0,0), true)
		};
		for(int i = 0; i < springs.length; i++)
		{
			Spring spring = springs[i];
			spring.setLinerUpperLimit(new Vector3(5,10,5));
			spring.setLinerLowerLimit(new Vector3(-5,-10,-5));
			spring.setAnglarLowerLimit(new Vector3(0,0,0));
			spring.setAnglarUpperLimit(new Vector3(0,0,0));
			spring.setupDof(Dof.translateX, 39.478f, 1f);
			spring.setupDof(Dof.translateY, 39.478f, 1f);
			spring.setupDof(Dof.translateZ, 39.478f, 1f);
			spring.setEquilibrumPoint();
		}
		ball[7].setLinearVelocity(new Vector3(10, 10,0));
		Logger.setLogFile("result");
	}
	
	public void render(GL10 gl)
	{
		if(count < 200000)
		{
			if(count % 10 == 0)
			{
				float energy = 0;
				for(int i = 0; i < springs.length; i++) energy += springs[i].getElasticPotentialEnergy();
				Logger.write(Float.toString(energy));
			}
		}
		else Logger.close();
		count++;
		for(int i = 0; i < ball.length; i++) ball[i].render(gl);
	}
}
