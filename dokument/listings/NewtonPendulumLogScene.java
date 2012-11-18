package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;
import org.bulletSamples.Logger;
import org.bulletSamples.geometry.*;
import org.bulletSamples.physics.*;

public class NewtonPendulumScene extends BaseScene {
	public NewtonPendulumScene(DynamicsWorld dw) { super(dw); }

	protected CollisionShape[] spheres;
	protected UniversalConstraint[] constraints;
	protected Sphere sphere;
	int licznik = 0;
	private String format = "%.2f";
	private float restitution = .95f;
	private float delta = .01f;
	private int numtests = 10;
	private int testCount = 0;
	
	public void create()
	{
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
		for(int i = 0; i < spheres.length; i++) spheres[i].setRestitution(restitution);
		spheres[5].setLinearVelocity(new Vector3(-5,0,0));
		constraints = new UniversalConstraint[spheres.length/2];
		for(int i = 0; i < spheres.length-1; i+=2)
		{
			UniversalConstraint uc = new UniversalConstraint(dw, spheres[i], spheres[i+1], spheres[i].getTranslation(), new Vector3(1,0,0),new Vector3(0,0,1));
	   		uc.setLowerLimit((float)(-Math.PI / 2.0), (float)(-Math.PI / 2.0));
	   		uc.setUpperLimit((float)(Math.PI / 2.0), (float)(Math.PI / 2.0));
			constraints[i/2] = uc;
		}
		Logger.setLogFile(String.format(format, restitution));
	}
	
	private void resetScene()
	{
		Logger.close();
		testCount++;
		restitution -= delta;
		for(int i = 0; i < spheres.length; i++) spheres[i].setRestitution(restitution);
		spheres[1].setTranslation(new Vector3(0f,2,-20));
		spheres[3].setTranslation(new Vector3(1f,2,-20));
		spheres[5].setTranslation(new Vector3(-1f,2,-20));
		spheres[7].setTranslation(new Vector3(2f,2,-20));
		spheres[1].setLinearVelocity(Vector3.zero());
		spheres[3].setLinearVelocity(Vector3.zero());
		spheres[5].setLinearVelocity(new Vector3(-5,0,0));
		spheres[7].setLinearVelocity(Vector3.zero());
		Logger.setLogFile(String.format(format, restitution));
	}
	
	public void render(GL10 gl)
	{
		for(int i = 0; i < spheres.length; i++) spheres[i].render(gl);
		for(int i = 0; i < constraints.length; i++) constraints[i].render(gl);
		licznik++;
		if(licznik % 10 == 0)
		{
			if(licznik < 4000)
			{
				float energy = 0;
				for(int i = 0; i < spheres.length; i++) energy += spheres[i].getLinearKineticEnergy() + spheres[i].getGravitationalPotentialEnergy();
				Logger.write(Float.toString(energy));
				System.out.println(restitution + ":" + licznik);
			}
			else
			{
				if(testCount < numtests) resetScene();
				licznik = 0;
			}
		}
	}
}
