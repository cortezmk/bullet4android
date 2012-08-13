package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.Cube;
import org.bulletSamples.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;

public class SimpleSphereScene extends BaseScene {
	protected CollisionShape box = null;
	protected CollisionShape box2 = null;
	protected Cube cube = null;
	protected Cube cube2 = null;
	
	public SimpleSphereScene(DynamicsWorld dw) {
		super(dw);
		// TODO Auto-generated constructor stub
	}
	
	public void create()
	{
		box = dw.createShape(CollisionShape.Shape.Sphere ,new Vector3(0, 1, -20), 1);
		box2 = dw.createShape(CollisionShape.Shape.Sphere, new Vector3(.5f, 7, -20.1f), 1);
		cube = new Cube(1,1,1);
		cube2 = new Cube(1,1,1);
	}
	
	public void render(GL10 gl)
	{
		cube.applyTransform(box);
		cube.draw(gl);
		cube2.applyTransform(box2);
		cube2.draw(gl);
	}
}
