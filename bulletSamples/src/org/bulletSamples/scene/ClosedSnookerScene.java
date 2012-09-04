package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Box;
import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;
import org.bulletSamples.Accelerometer;

public class ClosedSnookerScene extends BaseScene {
	public ClosedSnookerScene(DynamicsWorld dw) { super(dw); }
	
	private Box tableBottom, tableVerticalPart, tableHorizontalPart;
	private CollisionShape[] table;
	private Camera camera;
	private Sphere ballShape;
	private CollisionShape[] ball;
	private int frameCounter = 0;
	
	public void create()
	{
		camera = new Camera(new Vector3(0, 60, 0), 0, 270);
		//camera.lookat = true;
		Camera.active = camera;
		tableBottom = new Box(18, 1, 36);
		tableBottom.setColor(.3f, 1, .3f, 1);
		tableVerticalPart = new Box(1, 1, 36);
		tableVerticalPart.setColor(.6f, .3f, 0, 1);
		tableHorizontalPart = new Box(18, 1, 1);
		tableHorizontalPart.setColor(.6f, .3f, 0, 1);
		table = new CollisionShape[]
		{
			dw.createShape(tableBottom, new Vector3(0,1,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(9.5f,2,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(-9.5f,2,0), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,18.5f), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,-18.5f), 0)
		};
		ballShape = new Sphere(.25f);
		ball = new CollisionShape[6*6+1];
		ball[0] = dw.createShape(ballShape, new Vector3(0,3,-12), 1);
		for(int i = 0; i < 6; i++) for(int j = 0; j < 6; j++) ball[i*6+j+1] = dw.createShape(ballShape, new Vector3(-1.25f+i/2.0f, 3, 7.25f+j/2.0f), 1);
		for(int i = 0; i < ball.length; i++)
		{
			ball[i].setRestitution(.95f);
			ball[i].setFriction(1);
		}
		ball[0].setLinearVelocity(new Vector3(0,0,100));
	}
	
	public void render(GL10 gl)
	{
		if(frameCounter % 10 == 0)
		{
			float ek = 0;
			for(int i = 0; i < ball.length; i++) ek += ball[i].getMass() * (float)Math.pow(ball[i].getLinearVelocity().length(), 2) / 2.0f;
			System.out.println("EK: " + ek);
		}
		frameCounter++;
		//Camera.active.position = Accelerometer.gravity.normalize().multiply(60);
		for(int i = 0; i < table.length; i++) table[i].render(gl);
		for(int i = 0; i < ball.length; i++) ball[i].render(gl);
	}
}