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

	private float mass;
	public float getMass() {
		return mass;
	}

	public int id;
	private int idShape;
	private int idMState;
	private int idWorld;
	native private void destructor(int id, int idWorld);
	native void NgetTranslation(int id, Vector3 vec);
	native void NsetTranslation(int id, Vector3 vec);
	native void NgetRotation(int id, Quaternion q);
	native void NsetRotation(int id, Quaternion q);
	native void NapplyCentralForce(int id, Vector3 vec);
	native void NapplyTorque(int id, Vector3 vec);
	native void NgetTransform(int id, Vector3 vec, Quaternion quat);
	native void NsetRestitution(int id, float value);
	native void NsetFriction(int id, float value);
	native void NsetLinearVelocity(int id, Vector3 value);
	native void NsetAngularVelocity(int id, Vector3 value);
	native void NgetLinearVelocity(int id, Vector3 value);
	native void NgetAngularVelocity(int id, Vector3 value);
	native float NgetInvMomentOfInertia(int id, Vector3 vec);
	
	public CollisionShape(Mesh mesh, float mass, int idworld)
	{
		this.mesh = mesh;
		this.mass = mass;
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
	
	public void render(GL10 gl)
	{
		mesh.applyTransform(this);
		mesh.render(gl);
	}
	
	public void setRestitution(float value)
	{
		NsetRestitution(id, value);
	}
	
	public void setFriction(float value)
	{
		NsetFriction(id, value);
	}
	
	public void setLinearVelocity(Vector3 value)
	{
		NsetLinearVelocity(id, value);
	}
	
	public void setAngularVelocity(Vector3 value)
	{
		NsetAngularVelocity(id, value);
	}
	
	public Vector3 getLinearVelocity()
	{
		Vector3 value = new Vector3();
		NgetLinearVelocity(id, value);
		return value;
	}
	
	public Vector3 getAngularVelocity()
	{
		Vector3 value = new Vector3();
		NgetAngularVelocity(id, value);
		return value;
	}
	
	protected void finalize() throws Throwable
	{
		//destructor(id, idWorld);
		super.finalize();
	}
	
	public void remove()
	{
		destructor(id, idWorld);
	}
	
	public float getLinearKineticEnergy()
	{
		return mass * (float)Math.pow(getLinearVelocity().length(), 2) / 2.0f;
	}
	
	public float getMomentOfInertia(Vector3 axis)
	{
		return 1.0f / NgetInvMomentOfInertia(id, axis);
	}
	
	public float getAngularKineticEnergy()
	{
		Vector3 vel = this.getAngularVelocity();
		if(vel.length() == 0) return 0;
		float inertia = getMomentOfInertia(this.getAngularVelocity().normalize());
		return inertia * (float)Math.pow(getAngularVelocity().length(), 2) / 2.0f;
	}
	
	public float getKineticEnergy()
	{
		return getAngularKineticEnergy() + getLinearKineticEnergy();
	}
}
