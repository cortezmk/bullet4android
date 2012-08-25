package org.bulletSamples.physics;

import javax.microedition.khronos.opengles.GL10;

import org.bulletSamples.geometry.Mesh;
import org.bulletSamples.geometry.Quaternion;
import org.bulletSamples.geometry.Vector3;

public class CollisionShape {
	private Mesh mesh;
	public Mesh getMesh() {
		return mesh;
	}

	public int id;
	private int idShape;
	private int idMState;
	native private void destructor(int id);
	native void NgetTranslation(int id, Vector3 vec);
	native void NsetTranslation(int id, Vector3 vec);
	native void NgetRotation(int id, Quaternion q);
	native void NsetRotation(int id, Quaternion q);
	native void NapplyCentralForce(int id, Vector3 vec);
	native void NapplyTorque(int id, Vector3 vec);
	native void NgetTransform(int id, Vector3 vec, Quaternion quat);
	native void NsetMass(int id, float mass);
	
	public CollisionShape(Mesh mesh)
	{
		this.mesh = mesh;
		id = Integer.MAX_VALUE;
	}
	
	public void getTransform(Vector3 vec, Quaternion quat)
	{
		NgetTransform(id, vec, quat);
	}
	
	public Vector3 getTranslation()
	{
		Vector3 ret = new Vector3();
		NgetTranslation(id, ret);
		return ret;
	}
	
	public Quaternion getRotation()
	{
		Quaternion ret = new Quaternion();
		NgetRotation(id, ret);
		return ret;
	}
	
	public void setRotation(Quaternion quat)
	{
		NsetRotation(id, quat);
	}
	
	public void setTranslation(Vector3 vec)
	{
		NsetTranslation(id, vec);
	}
	
	public void applyCentralForce(Vector3 vec)
	{
		NapplyCentralForce(id, vec);
	}
	
	public void applyTorque(Vector3 vec)
	{
		NapplyCentralForce(id, vec);
	}
	
	public void setMass(float mass)
	{
		NsetMass(id, mass);
	}
	
	public void render(GL10 gl)
	{
		mesh.applyTransform(this);
		mesh.render(gl);
	}
	
	protected void finalize() throws Throwable
	{
		destructor(id);
		super.finalize();
	}
}
