package org.bulletSamples.geometry;

public class Camera {
	public static Camera active = null;
	
	public Camera(Vector3 position, Quaternion rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
	public Camera(Vector3 position, float yaw, float pitch)
	{
		this.position = position;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public float yaw = 0;
	public float pitch = 0;
	public Vector3 position;
	public Quaternion rotation;
	public Vector3 target = new Vector3(0,0,0);
	public boolean lookat = false;
}
