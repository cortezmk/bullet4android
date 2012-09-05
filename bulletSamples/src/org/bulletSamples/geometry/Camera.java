package org.bulletSamples.geometry;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

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
	
	public static void applyTransform(GL10 gl)
	{
    	if(Camera.active != null)
    	{
            if(Camera.active.lookat)
            {
            	Vector3 eye = Camera.active.position;
            	Vector3 center = Camera.active.target;
            	Vector3 up = eye.rotate(new Quaternion(eye.cross(Vector3.up()), (float)Math.PI/2.0f));
            	GLU.gluLookAt(gl, eye.x, eye.y, eye.z, center.x, center.y, center.z, 1, 0, up.z);
            }
            else
    		{
    			gl.glRotatef(-Camera.active.yaw, 0, 1, 0);
    			gl.glRotatef(-Camera.active.pitch, 1, 0, 0);
    			gl.glTranslatef(-Camera.active.position.x, -Camera.active.position.y, -Camera.active.position.z);
    		}
    	}
	}
	
	public float yaw = 0;
	public float pitch = 0;
	public Vector3 position;
	public Quaternion rotation;
	public Vector3 target = new Vector3(0,0,0);
	public boolean lookat = false;
}
