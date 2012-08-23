package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Box;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.CollisionShape;
import org.bulletSamples.physics.DynamicsWorld;

public class SimpleScene extends BaseScene {
	protected CollisionShape box = null;
	protected CollisionShape box2 = null;
	protected Box cube = null;
	protected Box cube2 = null;
	
	public SimpleScene(DynamicsWorld dw) {
		super(dw);
		// TODO Auto-generated constructor stub
	}
	
	public void create()
	{
		box = dw.createShape(new Vector3(0, 1, -20));
		box2 = dw.createShape(new Vector3(.5f, 7, -20));
		cube = new Box(1,1,1);
		cube2 = new Box(1,1,1);
	}
	
	public void render(GL10 gl)
	{
		cube.applyTransform(box);
		cube.render(gl);
		cube2.applyTransform(box2);
		cube2.render(gl);
	}
}
