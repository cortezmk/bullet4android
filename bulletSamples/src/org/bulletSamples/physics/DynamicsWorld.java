package org.bulletSamples.physics;
import org.bulletSamples.geometry.Shape;
import org.bulletSamples.geometry.Vector3;

public class DynamicsWorld {
	public int id;
	
	native private int constructor();
	native private void destructor(int id);
	native private void NsetGravity(int id, float x, float y, float z);
	native private void NaddBoxShape(int id, int idShape);
	native private void NstepSimulation(int id, int timeStep);
	native private void NgetTransform(int id, Vector3 ret);
	native private int BlaCreateRigidBody(int id, Vector3 pos);
	native private int NCreateBox(int id, float mass, Vector3 pos);
	native private int NCreateSphere(int id, float mass, Vector3 pos);
	
	native private int Bla0();
	
	public DynamicsWorld()
	{
		id = constructor();
	}
	
	public CollisionShape createShape(Vector3 pos)
	{
		CollisionShape shape = new CollisionShape();
		shape.id = NCreateBox(id, 1, pos);
		return shape;
	}
	
	public CollisionShape createShape(Vector3 pos, float mass)
	{
		CollisionShape shape = new CollisionShape();
		shape.id = NCreateBox(id, mass, pos);
		return shape;
	}
	
	public CollisionShape createShape(Shape shape, Vector3 pos, float mass)
	{
		CollisionShape cShape = new CollisionShape();
		switch(shape)
		{
			case Box:
				cShape.id = NCreateBox(id, mass, pos);
				break;
			case Sphere:
				cShape.id = NCreateSphere(id, mass, pos);
				break;	
		}
		return cShape;
	}
	
	protected void finalize() throws Throwable
	{
		destructor(id);
		super.finalize();
	}
	
	public void setGravity(Vector3 gravity)
	{
		NsetGravity(id, gravity.x, gravity.y, gravity.z);
	}
	
	public void addBoxShape(CollisionShape shape)
	{
		NaddBoxShape(id, shape.id);
	}
	
	public void stepSimulation(int timeStep)
	{
		NstepSimulation(id, timeStep);
	}
	
	public Vector3 getTransform()
	{
		Vector3 ret = new Vector3(0, 0, 0);
		NgetTransform(id, ret);
		return ret;
	}
}
