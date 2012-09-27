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

public class SimpleSnookerSceneSeparatedEnergies extends BaseScene {
	public SimpleSnookerSceneSeparatedEnergies(DynamicsWorld dw) { super(dw); }
	
	private Box tableBottom, tableVerticalPart, tableHorizontalPart;
	private CollisionShape[] table;
	private Camera camera;
	private Sphere ballShape;
	private Box box;
	public CollisionShape ball;
	private int frameCounter = 0;
	private float restitution = 0.90f;
	
	float deltaRest = .01f;
	int numTests = 10;
	int maxBounces = 60;
	int numBounces = 0;
	int numTest = 0;
	private boolean negativeSign = false;
	int ticks = 0;
	int maxTicks = 2000;
	String format = "%.2f";
	
	double sumEK = 0;
	float[] avgEK = new float[numTests];
	
	private boolean getSign(float val)
	{
		return val > 0 ? false : true;
	}
	
	public void create()
	{
   		//dw.simulationSubSteps = 120;
   		//dw.fixedStep = (float)(1.0/240.0);
		camera = new Camera(new Vector3(0, 20, 0), 0, 270);
		//camera.lookat = true;
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
		box = new Box(.25f, .25f, .25f);
		ball = dw.createShape(box, new Vector3(0,1.75f,0), 1);
		ball.setRestitution(restitution);
		ball.setFriction(0);
		ball.setLinearVelocity(new Vector3(0,0,-5));
		resetSimulation(restitution, false);
		negativeSign = getSign(ball.getLinearVelocity().z);
		Logger.setLogFile(String.format(format, restitution));
	}
	
	public void resetSimulation(float restitution, boolean reload)
	{
		if(reload)
		{
			ball.setTranslation(new Vector3(-100,0,0));
			//ball.remove();
			ball = dw.createShape(box, new Vector3(0,1.75f,0), 1);
		}
		ball.setTranslation(new Vector3(0, 1.75f, 0));
		//ball.setRotation(new Quaternion(Vector3.up(), 0));
		ball.setLinearVelocity(new Vector3(0,0,-5));
		ball.setRestitution(restitution);
		for(int i = 1; i < table.length; i++) table[i].setRestitution(restitution);
	}
	
	public void render(GL10 gl)
	{
		if(numTest < numTests)
		{
			if(negativeSign != getSign(ball.getLinearVelocity().z))
			{
				sumEK += ball.getKineticEnergy();
				negativeSign = !negativeSign;
				numBounces++;
				Logger.write(ball.getLinearKineticEnergy() + " " + ball.getAngularKineticEnergy() + " ");
				ticks = 0;
			}
			float z = ball.getTranslation().z;
			if(numBounces > maxBounces || ticks > maxTicks || ball.getLinearVelocity().length() == 0 || z > 100 || z < -100)
			{
				if(ball.getLinearVelocity().length() == 0)
				{
					for(int i = numBounces; i < maxBounces+1; i++) Logger.write("0.0 0.0");
					//numTest++;
				}
				else if(numBounces > maxBounces)
				{
					//numTest++;
				}
				ticks = 0;
				avgEK[numTest] = (float)(sumEK / (float)maxBounces);
				//System.out.println(restitution + (float)numTest*deltaRest + ":" + avgEK[numTest]);
				sumEK = 0;
				numBounces = 0;
				resetSimulation(restitution + (float)numTest*deltaRest, true);
				Logger.close();
				Logger.setLogFile(String.format(format, restitution + (float)numTest*deltaRest));
				numTest++;
			}
			//if(ball.getLinearVelocity().length() == 0) ball.setLinearVelocity(new Vector3(0,0,-5));
			ticks++;
		}
		//Camera.active.position = Accelerometer.gravity.normalize().multiply(60);
		for(int i = 0; i < table.length; i++) table[i].render(gl);
		ball.render(gl);
	}
}