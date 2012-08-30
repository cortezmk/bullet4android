package org.bulletSamples.scene;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Camera;
import org.bulletSamples.geometry.Quaternion;
import org.bulletSamples.geometry.Vector3;
import org.bulletSamples.physics.DynamicsWorld;

import android.view.MotionEvent;

public class BaseScene {
	public static int width = 0;
	public static int height = 0;
	protected DynamicsWorld dw;
	public boolean enableObjectDrag = false;
	public BaseScene(DynamicsWorld dw)
	{
		this.dw = dw;
	}
	public void create() { }
	public void update() { }
	public void render(GL10 gl) { }
	public void onTouch(MotionEvent e)
	{
		if(!enableObjectDrag) return;
		int x = (int)e.getX();
		int y = (int)e.getY();
		int eventaction = e.getAction();
		Vector3 rayFrom;
		if(Camera.active != null) rayFrom = Camera.active.position.clone();
		else rayFrom = Vector3.zero();
	    switch (eventaction ) { 
	    	case MotionEvent.ACTION_DOWN:
	    		dw.pickObject(rayFrom, getRayTo(x, y));
	    		break;
	    	case MotionEvent.ACTION_MOVE:
	    		dw.dragObject(rayFrom, getRayTo(x, y));
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		dw.dropObject(rayFrom, getRayTo(x, y));
	    		break;
	    }
	}
	private Vector3 getRayTo(int x, int y)
	{
		float top = 1.f;
		float bottom = -1.f;
		float nearPlane = 1.f;
		float tanFov = (top-bottom)*0.5f / nearPlane;
		float fov = (float)(Math.PI/4.0);//2.0f * (float)Math.atan(tanFov);
		Vector3 rayFrom = Vector3.zero();
		Vector3 rayForward = new Vector3(0,0,-1);
		if(Camera.active != null)
		{
			rayFrom = Camera.active.position.clone();
			if(Camera.active.lookat) rayForward = Camera.active.target.subtract(Camera.active.position).normalize();
			else
			{
				Quaternion yaw = new Quaternion(new Vector3(0,1,0), Camera.active.yaw * (float)(Math.PI/180.0));
				Quaternion pitch = new Quaternion(new Vector3(1,0,0), Camera.active.pitch * (float)(Math.PI/180.0));
				rayForward = rayForward.rotate(yaw).rotate(pitch).normalize();
			}
		}
		float farPlane = 100.f;
		rayForward = rayForward.multiply(farPlane);
		Vector3 rightOffset;
		Vector3 vertical = Vector3.up();//rayFrom.rotate(new Quaternion(rayFrom.cross(Vector3.up()), (float)Math.PI/2.0f));
		Vector3 hor = rayForward.cross(vertical).normalize();
		vertical = hor.cross(rayForward).normalize();
		float tanfov = (float)Math.tan(.5f * fov);
		hor = hor.multiply(2.0f * farPlane * tanfov);
		vertical = vertical.multiply(2.0f * farPlane * tanfov);
		hor = hor.multiply((float)width / (float)height);//if(width < height) hor = hor.multiply((float)width / (float)height);
		//else vertical = vertical.multiply((float)width / (float)height);
		Vector3 rayToCenter = rayFrom.add(rayForward);
		Vector3 dHor = hor.multiply(1.0f/(float)width);
		Vector3 dVert = vertical.multiply(1.0f/(float)height);
		Vector3 rayTo = rayToCenter.subtract(hor.multiply(.5f).subtract(vertical.multiply(.5f)));
		rayTo = rayTo.add(dHor.multiply((float)x));
		rayTo = rayTo.subtract(dVert.multiply((float)y));
		return rayTo;
	}
}
