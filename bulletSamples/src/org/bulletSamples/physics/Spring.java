package org.bulletSamples.physics;
import org.bulletSamples.geometry.*;

public class Spring {
	public int id;
	
	private native int constructor(int dynamicsWorld, int rb1, int rb2, Vector3 frameA, Vector3 frameB, boolean linear);
	private native void NsetEquilibriumPoint(int id);
	private native void NsetLinearUpperLimit(int id, Vector3 vec);
	private native void NsetLinearLowerLimit(int id, Vector3 vec);
	private native void NsetAngularUpperLimit(int id, Vector3 vec);
	private native void NsetAngularLowerLimit(int id, Vector3 vec);
	private native void Nenable(int id, int index, boolean enable);
	private native void NsetDamping(int id, int index, float damping);
	private native void NsetStiffness(int id, int index, float stiffness);
	private native void destructor();
	
	private CollisionShape rb1, rb2;
	private Vector3 relation;
	private float stiffness;
	
	public Spring(DynamicsWorld dw, CollisionShape rb1, CollisionShape rb2, Vector3 frameA, Vector3 frameB, boolean linear)
	{
		id = constructor(dw.id, rb1.id, rb2.id, frameA, frameB, linear);
		this.rb1 = rb1;
		this.rb2 = rb2;
		relation = rb2.getTranslation().subtract(rb1.getTranslation());
	}
	
	public void setupDof(Dof index, float stiffness, float damping)
	{
		enable(index, true);
		setDamping(index, damping);
		setStiffness(index, stiffness);
		this.stiffness = stiffness;
	}
	
	public void enable(Dof index, boolean enable)
	{
		Nenable(id, index.toInt(), enable);
	}
	
	public void setDamping(Dof index, float damping)
	{
		NsetDamping(id, index.toInt(), damping);
	}
	
	public void setStiffness(Dof index, float stiffness)
	{
		NsetStiffness(id, index.toInt(), stiffness);
	}
	
	public void setEquilibrumPoint()
	{
		NsetEquilibriumPoint(id);
	}
	
	public void setLinerUpperLimit(Vector3 vec)
	{
		NsetLinearUpperLimit(id, vec);
	}
	
	public void setLinerLowerLimit(Vector3 vec)
	{
		NsetLinearLowerLimit(id, vec);
	}
	
	public void setAnglarUpperLimit(Vector3 vec)
	{
		NsetAngularUpperLimit(id, vec);
	}
	
	public void setAnglarLowerLimit(Vector3 vec)
	{
		NsetAngularLowerLimit(id, vec);
	}
	
	protected void finalize() throws Throwable
	{
		//destructor();
		super.finalize();
	}
	
	public float getElasticPotentialEnergy()
	{
		Vector3 centerPos = relation.clone().rotate(rb1.getRotation()).add(rb1.getTranslation());
		float x = centerPos.subtract(rb2.getTranslation()).length();
		return (float)Math.pow(x, 2)*stiffness/2.0f;
	}
}
