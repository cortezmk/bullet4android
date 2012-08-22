package org.bulletSamples.physics;
import org.bulletSamples.geometry.Vector3;

public class Spring {
	public int id;
	
	private native int constructor(int dynamicsWorld, int rb1, int rb2, Vector3 frameA, Vector3 frameB, boolean linear);
	private native void Nenable(int id, int index, boolean enable);
	private native void NsetDamping(int id, int index, float damping);
	private native void NsetStiffness(int id, int index, float stiffness);
	private native void NsetEquilibriumPoint(int id);
	private native void NsetLinearUpperLimit(int id, Vector3 vec);
	private native void NsetLinearLowerLimit(int id, Vector3 vec);
	private native void NsetAngularUpperLimit(int id, Vector3 vec);
	private native void NsetAngularLowerLimit(int id, Vector3 vec);
	
	public Spring(DynamicsWorld dw, CollisionShape rb1, CollisionShape rb2, Vector3 frameA, Vector3 frameB, boolean linear)
	{
		id = constructor(dw.id, rb1.id, rb2.id, frameA, frameB, linear);
	}
	
	public void enable(int index, boolean enable)
	{
		Nenable(id, index, enable);
	}
	
	public void setDamping(int index, float damping)
	{
		NsetDamping(id, index, damping);
	}
	
	public void setStifness(int index, float stiffness)
	{
		NsetDamping(id, index, stiffness);
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
}
