package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Box;
import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;
import org.bulletSamples.Accelerometer;

public class SnookerScene extends BaseScene {
	public SnookerScene(DynamicsWorld dw) { super(dw); }
	
	private Box tableBottom, tableVerticalPart, tableHorizontalPart;
	private CollisionShape[] table;
	private Camera camera;
	private Sphere ballShape;
	private CollisionShape[] ball;
	
	public void create()
	{
		camera = new Camera(new Vector3(0, 60, 0), 0, 270);
		camera.lookat = true;
		Camera.active = camera;
		tableBottom = new Box(18, 1, 36);
		tableBottom.setColor(.3f, 1, .3f, 1);
		tableVerticalPart = new Box(1, 1, 17);
		tableVerticalPart.setColor(.6f, .3f, 0, 1);
		tableHorizontalPart = new Box(17, 1, 1);
		tableHorizontalPart.setColor(.6f, .3f, 0, 1);
		table = new CollisionShape[]
		{
			dw.createShape(tableBottom, new Vector3(0,1,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(9.5f,2,9), 0),
			dw.createShape(tableVerticalPart, new Vector3(9.5f,2,-9), 0),
			dw.createShape(tableVerticalPart, new Vector3(-9.5f,2,9), 0),
			dw.createShape(tableVerticalPart, new Vector3(-9.5f,2,-9), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,18.5f), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,-18.5f), 0)
		};
		ballShape = new Sphere(.25f);
		ball = new CollisionShape[]
		{
			dw.createShape(ballShape, new Vector3(0,3,0), 1),
			dw.createShape(ballShape, new Vector3(0,4,0), 1),
			dw.createShape(ballShape, new Vector3(0,5,0), 1),
			dw.createShape(ballShape, new Vector3(0,6,0), 1),
			dw.createShape(ballShape, new Vector3(0,7,0), 1),
			dw.createShape(ballShape, new Vector3(0,8,0), 1),
			dw.createShape(ballShape, new Vector3(0,9,0), 1),
			dw.createShape(ballShape, new Vector3(0,10,0), 1),
			dw.createShape(ballShape, new Vector3(0,11,0), 1),
			dw.createShape(ballShape, new Vector3(0,12,0), 1),
			dw.createShape(ballShape, new Vector3(0,13,0), 1),
			dw.createShape(ballShape, new Vector3(0,14,0), 1),
			dw.createShape(ballShape, new Vector3(0,15,0), 1),
			dw.createShape(ballShape, new Vector3(0,16,0), 1),
			dw.createShape(ballShape, new Vector3(0,17,0), 1),
			dw.createShape(ballShape, new Vector3(0,18,0), 1)
		};
		for(int i = 0; i < ball.length; i++)
		{
			ball[i].setRestitution(.95f);
			ball[i].setFriction(1);
		}
	}
	
	public void render(GL10 gl)
	{
		Camera.active.position = Accelerometer.gravity.normalize().multiply(60);
		for(int i = 0; i < table.length; i++)
		{
			table[i].render(gl);
		}
		for(int i = 0; i < ball.length; i++)
		{
			ball[i].render(gl);
		}
	}
}
