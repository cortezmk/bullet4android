package org.bulletSamples.geometry;

public class Quaternion implements Cloneable{
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
	
	public Quaternion negate()
	{
		return new Quaternion(axis, -angle);
	}
	
	public Quaternion clone()
	{
		return new Quaternion(new Vector3(axis.x, axis.y, axis.z), angle);
	}
}
