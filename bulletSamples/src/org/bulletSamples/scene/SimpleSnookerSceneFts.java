package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;
import org.bulletSamples.geometry.*;
import org.bulletSamples.physics.*;
import org.bulletSamples.Logger;

public class SimpleSnookerSceneFts extends BaseScene {
	public SimpleSnookerSceneFts(DynamicsWorld dw) { super(dw); }
	
	private Box tableBottom, tableVerticalPart, tableHorizontalPart;
	private CollisionShape[] table;
	private Camera camera;
	private Sphere ballShape;
	public CollisionShape ball;
	private int frameCounter = 0;
	private float restitution = 1f;
	
	private float ftsFactor = 60;
	private float deltaFts = 60;
	int numTests = 10;
	int maxBounces = 60;
	int numBounces = 0;
	int numTest = 0;
	private boolean negativeSign = false;
	int ticks = 0;
	int maxTicks = 2000;
	String format = "%.1f";
	
	private boolean getSign(float val)
	{
		return val > 0 ? false : true;
	}
	
	public void create()
	{
   		dw.simulationSubSteps = 300;
   		dw.fixedStep = (float)(1.0/ftsFactor);
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
			table[i].setFriction(0);
		}
		table[0].setRestitution(0f);
		ballShape = new Sphere(.25f);
		ball = dw.createShape(ballShape, new Vector3(0,1.75f,0), 1);
		ball.setRestitution(restitution);
		ball.setFriction(0);
		ball.setLinearVelocity(new Vector3(0,0,-12));
		negativeSign = getSign(ball.getLinearVelocity().z);
		Logger.setLogFile(String.format(format, ftsFactor));
	}
	
	public void resetSimulation(float ftsFactor, boolean reload)
	{
		if(reload)
		{
			ball.setTranslation(new Vector3(-100,0,0));
			ball = dw.createShape(ballShape, new Vector3(0,1.75f,0), 1);
		}
		ball.setTranslation(new Vector3(0, 1.75f, 0));
		ball.setLinearVelocity(new Vector3(0,0,-12));
		ball.setRestitution(restitution);
		dw.fixedStep = 1.0f/ftsFactor;
	}
	
	public void render(GL10 gl)
	{
		if(numTest < numTests)
		{
			if(negativeSign != getSign(ball.getLinearVelocity().z))
			{
				negativeSign = !negativeSign;
				numBounces++;
				Logger.write(ball.getKineticEnergy() + " ");
				ticks = 0;
			}
			float z = ball.getTranslation().z;
			if(numBounces > maxBounces || ticks > maxTicks || ball.getLinearVelocity().length() == 0 || z > 100 || z < -100)
			{
				numTest++;
				if(ball.getLinearVelocity().length() == 0)
				{
					for(int i = numBounces; i < maxBounces+1; i++) Logger.write("0.0 ");
				}
				ticks = 0;
				numBounces = 0;
				resetSimulation(ftsFactor + (float)numTest*deltaFts, true);
				Logger.close();
				Logger.setLogFile(String.format(format, ftsFactor + (float)numTest*deltaFts));
			}
			ticks++;
		}
		for(int i = 0; i < table.length; i++) table[i].render(gl);
		ball.render(gl);
	}
}