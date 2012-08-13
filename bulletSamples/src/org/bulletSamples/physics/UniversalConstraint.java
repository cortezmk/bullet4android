package org.bulletSamples.physics;

import org.bulletSamples.Vector3;

public class UniversalConstraint {
	public int id;
	
	private native int constructor(int dynamicsWorld, int shape1, int shape2, Vector3 anchor, Vector3 axis1, Vector3 axis2);
	private native void NsetUpperLimit(int id, float ang1max, float ang2max);
	private native void NsetLowerLimit(int id, float ang1min, float ang2min);
	private native void NsetAxis(int id, Vector3 axis1, Vector3 axis2);
	
	public UniversalConstraint(DynamicsWorld dw, CollisionShape shape1, CollisionShape shape2, Vector3 anchor, Vector3 axis1, Vector3 axis2)
	{
		id = constructor(dw.id, shape1.id, shape2.id, anchor, axis1, axis2);
	}
	
	public void setUpperLimit(float ang1max, float ang2max)
	{
		NsetUpperLimit(id, ang1max, ang2max);
	}
	
	public void setLowerLimit(float ang1min, float ang2min)
	{
		NsetLowerLimit(id, ang1min, ang2min);
	}
	
	public void setAxis(Vector3 axis1, Vector3 axis2)
	{
		NsetAxis(id, axis1, axis2);
	}
}
