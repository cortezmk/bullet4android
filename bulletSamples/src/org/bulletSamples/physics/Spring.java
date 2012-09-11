package org.bulletSamples.physics;
import org.bulletSamples.geometry.Vector3;

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
	
	public Spring(DynamicsWorld dw, CollisionShape rb1, CollisionShape rb2, Vector3 frameA, Vector3 frameB, boolean linear)
	{
		id = constructor(dw.id, rb1.id, rb2.id, frameA, frameB, linear);
		
	}
	
	public void setupDof(Dof index, float stiffness, float damping)
	{
		enable(index, true);
		setDamping(index, damping);
		setStiffness(index, stiffness);
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
}
