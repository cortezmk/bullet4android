package org.bulletSamples.geometry;

public class Vector3 implements Cloneable {
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
	
	public static Vector3 up() { return new Vector3(0,1,0); };
	public static Vector3 down() { return new Vector3(0,-1,0); };
	public static Vector3 zero() { return new Vector3(0,0,0); }
	public static Vector3 front() { return new Vector3(0,0,-1); }
	public static Vector3 back() { return new Vector3(0,0,1); }
	public static Vector3 left() { return new Vector3(-1,0,0); }
	public static Vector3 right() { return new Vector3(1,0,0); }
	
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
	
	public Vector3 multiply(float scalar)
	{
		return new Vector3(x*scalar, y*scalar, z*scalar);
	}
	
	public Vector3 rotate(Quaternion q)
	{
		if(q.axis.x == 0 && q.axis.y == 0 && q.axis.z == 0) return new Vector3(1,0,0);
		Vector3 axis = q.axis.normalize();
		Vector3 o = axis.multiply(axis.dot(this));
		Vector3 x = this.subtract(o);
		Vector3 y = axis.cross(this);
		return o.add(x.multiply((float)Math.cos(q.angle))).add(y.multiply((float)Math.sin(q.angle)));
	}
	
	public Vector3 clone()
	{
		return new Vector3(x,y,z);
	}
	
	public boolean nanGuard()
	{
		if(Float.isNaN(x)) return true;
		if(Float.isNaN(y)) return true;
		if(Float.isNaN(z)) return true;
		return false;
	}
}
