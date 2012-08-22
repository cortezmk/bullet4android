package org.bulletSamples.geometry;

public class Vector3 {
	public float x;
	public float y;
	public float z;
	
	public Vector3()
	{
		x = 0; y = 0; z = 0;
	}
	
	public Vector3(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3 up = new Vector3(0,1,0);
	
	public Vector3 cross(Vector3 other)
	{
		return new Vector3(y*other.z - z*other.y, z*other.x - x*other.z, x*other.y - y*other.x);
	}
	
	public float length()
	{
		return (float)Math.pow(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2), .5f);
	}
	
	public Vector3 normalize()
	{
		float len = length();
		return new Vector3(x/len, y/len, z/len);
	}
	
	public float dot(Vector3 other)
	{
		return x*other.x + y*other.y + z*other.z;
	}
	
	public Vector3 add(Vector3 other)
	{
		return new Vector3(x+other.x, y+other.y, z+other.z);
	}
	
	public Vector3 negate()
	{
		return new Vector3(-x, -y, -z);
	}
	
	public Vector3 subtract(Vector3 other)
	{
		return add(other.negate());
	}
}
