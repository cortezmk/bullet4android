package org.bulletSamples.geometry;

public class Quaternion {
	public Vector3 axis;
	public float angle;
	
	public Quaternion()
	{
		this.axis = new Vector3();
		this.angle = 0;
	}
	
	public Quaternion(Vector3 axis, float angle)
	{
		this.axis = axis;
		this.angle = angle;
	}
}
