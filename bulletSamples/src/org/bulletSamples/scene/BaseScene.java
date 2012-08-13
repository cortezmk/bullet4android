package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.physics.DynamicsWorld;

public class BaseScene {
	protected DynamicsWorld dw;
	public BaseScene(DynamicsWorld dw)
	{
		this.dw = dw;
	}
	public void create() { }
	public void update() { }
	public void render(GL10 gl) { }
}
