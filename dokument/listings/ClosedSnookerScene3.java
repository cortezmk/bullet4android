package org.bulletSamples.scene;

import java.util.Random;
import javax.microedition.khronos.opengles.GL10;
import org.bulletSamples.geometry.*;
import org.bulletSamples.physics.*;
import org.bulletSamples.Logger;

public class MultiBodyTest extends BaseScene {
	public MultiBodyTest(DynamicsWorld dw) { super(dw); }
	
	private Box tableBottom, tableVerticalPart, tableHorizontalPart;
	private CollisionShape[] table;
	private Camera camera;
	private Sphere ballShape;
	private CollisionShape[] ball;
	private int frameCounter = 0;
	
	private int rowObjects = 3;
	private int colObjects = 3;
	private Random random = new Random();
	private int rendersInTick = 20;
	private int ticks = 0;
	private int maxTicks = 100;
	private int numTests = 10;
	private int test = 0;
	
	private float ftsFactor = 60;
	private float deltaFts = 60;
	private String format = "%.1f";
	
	public void create()
	{
		dw.simulationSubSteps = 120;
		dw.fixedStep = 1.0f/60.0f;
		camera = new Camera(new Vector3(0, 60, 0), 0, 270);
		Camera.active = camera;
		tableBottom = new Box(18, 1, 18);
		tableBottom.setColor(.3f, 1, .3f, 1);
		tableVerticalPart = new Box(1, 1, 18);
		tableVerticalPart.setColor(.6f, .3f, 0, 1);
		tableHorizontalPart = new Box(18, 1, 1);
		tableHorizontalPart.setColor(.6f, .3f, 0, 1);
		table = new CollisionShape[]
		{
			dw.createShape(tableBottom, new Vector3(0,1,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(9.5f,2,0), 0),
			dw.createShape(tableVerticalPart, new Vector3(-9.5f,2,0), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,9.5f), 0),
			dw.createShape(tableHorizontalPart, new Vector3(0,2,-9.5f), 0)
		};
		for(int i = 0; i < table.length; i++)
		{
			table[i].setRestitution(1);
			table[i].setFriction(0);
		}
		table[0].setRestitution(0);
		ballShape = new Sphere(.25f);
		ball = new CollisionShape[rowObjects*colObjects];
		for(int i = 0; i < rowObjects; i++)
		{
			for(int j = 0; j < colObjects; j++)
			{
				ball[i*rowObjects+j] = dw.createShape(ballShape, new Vector3(-6.0f + 16.0f/rowObjects*i, 3, -6.0f + 16.0f/colObjects*j), 1);
			}
		}
		for(int i = 0; i < ball.length; i++)
		{
			ball[i].setRestitution(1);
			ball[i].setFriction(0);
			ball[i].setLinearVelocity(new Vector3(0,0,5).rotate(new Quaternion(new Vector3(0,1,0), random.nextFloat()*2.0f*(float)Math.PI)));
		}
		Logger.setLogFile(String.format(format, ftsFactor));
	}
	
	public void resetSimulation(float ftsFactor)
	{
		for(int i = 0; i < rowObjects; i++)
		{
			for(int j = 0; j < colObjects; j++)
			{
				ball[i*rowObjects+j].setTranslation(new Vector3(-6.0f + 16.0f/rowObjects*i, 3, -6.0f + 16.0f/colObjects*j));
			}
		}
		for(int i = 0; i < ball.length; i++)
		{
			ball[i].setLinearVelocity(new Vector3(0,0,5).rotate(new Quaternion(new Vector3(0,1,0), random.nextFloat()*2.0f*(float)Math.PI)));
		}
		dw.fixedStep = 1.0f/ftsFactor;
	}
	
	public void render(GL10 gl)
	{
		if(test < numTests)
		{
			if(ticks > maxTicks)
			{
				ticks = 0;
				frameCounter = 0;
				test++;
				resetSimulation(ftsFactor + (float)test * deltaFts);
				Logger.close();
				Logger.setLogFile(String.format(format, ftsFactor + (float)test * deltaFts));
			}
			if(frameCounter % rendersInTick == 0)
			{
				float ek = 0;
				for(int i = 0; i < ball.length; i++) ek += ball[i].getKineticEnergy();
				Logger.write(ek + " ");
				ticks++;
			}
			frameCounter++;
		}
		for(int i = 0; i < table.length; i++) table[i].render(gl);
		for(int i = 0; i < ball.length; i++) ball[i].render(gl);
	}
}