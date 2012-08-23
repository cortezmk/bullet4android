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
		cube = new Box(1,1,1);
		cube2 = new Box(1,1,1);
		box = dw.createShape(cube, new Vector3(0, 1, -20), 1);
		box2 = dw.createShape(cube2, new Vector3(.5f, 7, -20), 1);
	}
	
	public void render(GL10 gl)
	{
		box.render(gl);
		box2.render(gl);
	}
}
