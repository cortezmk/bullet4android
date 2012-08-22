package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.*;
import org.bulletSamples.geometry.Cube;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.*;

public class NewtonPendulumScene extends BaseScene {
	public NewtonPendulumScene(DynamicsWorld dw) { super(dw); }

	protected CollisionShape[] boxes;
	protected UniversalConstraint[] constraints;
	protected Cube cube;
	
	public void create()
	{
		boxes = new CollisionShape[] {
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(0f,7,-20), 0),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(0f,2,-20), 10),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(1f,7,-20), 0),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(1f,2,-20), 10),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(-1f,7,-20), 0),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(-1f,2,-20), 10),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(2f,7,-20), 0),
			dw.createShape(CollisionShape.Shape.Sphere, new Vector3(2f,2f,-20), 10)
		};
		boxes[5].applyCentralForce(new Vector3(-2000,0,0));
		constraints = new UniversalConstraint[boxes.length/2];
		for(int i = 0; i < boxes.length-1; i+=2)
		{
			UniversalConstraint uc = new UniversalConstraint(dw, boxes[i], boxes[i+1], boxes[i].getTranslation(), new Vector3(1,0,0),new Vector3(0,0,1));
	   		uc.setLowerLimit((float)(-Math.PI / 2.0), (float)(-Math.PI / 2.0));
	   		uc.setUpperLimit((float)(Math.PI / 2.0), (float)(Math.PI / 2.0));
			constraints[i/2] = uc;
		}
		cube = new Cube(1, 1, 1);
	}
	
	public void render(GL10 gl)
	{
		for(int i = 0; i < boxes.length; i++)
		{
			cube.applyTransform(boxes[i]);
			cube.draw(gl);
		}
	}
}
