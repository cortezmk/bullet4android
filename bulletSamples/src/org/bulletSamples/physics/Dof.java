package org.bulletSamples.physics;

public enum Dof {
	translateX(0),
	translateY(1),
	translateZ(2),
	rotateX(3),
	rotateY(4),
	rotateZ(5);
	
	private final int value;
	private Dof(int value)
	{
		this.value = value;
	}
	public int toInt()
	{
		return value;
	}
}
