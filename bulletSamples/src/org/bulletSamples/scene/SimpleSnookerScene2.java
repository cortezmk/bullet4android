package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;
import org.bulletSamples.geometry.Box;
import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Quaternion;
import org.bulletSamples.geometry.Sphere;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;
import org.bulletSamples.Accelerometer;
import org.bulletSamples.Logger;

public class SimpleSnookerScene2 extends BaseScene {
	public SimpleSnookerScene2(DynamicsWorld dw) { super(dw); }
	
	private Box tableBottom, tableVerticalPart, tableHorizontalPart;
	private CollisionShape[] table;
	private Camera camera;
	private Sphere ballShape;
	private Box box;
	public CollisionShape ball;
	private float restitution = .99f;

	public void create()
	{
   		dw.simulationSubSteps = 300;
   		dw.fixedStep = (float)(1.0/450.00000640);
   		
		camera = new Camera(new Vector3(0, 20, 0), 0, 270);
		Camera.active = camera;
		tableBottom = new Box(3f, 1, 3f);
		tableBottom.setColor(.3f, 1, .3f, 1);
		tableVerticalPart = new Box(1, 1, 3f);
		tableVerticalPart.setColor(.6f, .3f, 0, 1);
		tableHorizontalPart = new Box(3f, 1, 1);
		tableHorizontalPart.setColor(.6f, .3f, 0, 1);
		table = new CollisionShape[]
		{
			dw.createShape(tableBottom, new Vector3(0,1,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(2f,2,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(-2f,2,0), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,2f), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,-2f), 0)
		};
		for(int i = 0; i < table.length; i++)
		{
			table[i].setRestitution(restitution);
			table[i].setFriction(.00f);
		}
		table[0].setRestitution(0f);
		ballShape = new Sphere(.25f);
		ball = dw.createShape(ballShape, new Vector3(0,1.75f,0), 1);
		ball.setRestitution(restitution);
		ball.setFriction(.00f);
		ball.setLinearVelocity(new Vector3(0,0,-10f));
	}
	
	public void render(GL10 gl)
	{
		for(int i = 0; i < table.length; i++) table[i].render(gl);
		ball.render(gl);
	}
}