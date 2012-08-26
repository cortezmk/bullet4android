package org.bulletSamples.physics;
import org.bulletSamples.geometry.*;
public class DynamicsWorld {
	public int id;
	private int idGRbody, idGMState, idGShape, idSolver, idDispatcher, idConfiguration, idBrodaphase;
	
	native private int constructor();
	native private void destructor();
	native private void NsetGravity(int id, float x, float y, float z);
	native private void NaddBoxShape(int id, int idShape);
	native private void NstepSimulation(int id, int timeStep);
	native private void NgetTransform(int id, Vector3 ret);
	native private int BlaCreateRigidBody(int id, Vector3 pos);
	native private void NCreateBox(CollisionShape cs, float mass, Vector3 pos, float width, float height, float depth);
	native private void NCreateSphere(CollisionShape cs, float mass, Vector3 pos, float radius);
	
	native private int Bla0();
	
	public DynamicsWorld()
	{
		id = constructor();
	}
	
	public CollisionShape createShape(Mesh mesh, Vector3 pos, float mass)
	{
		CollisionShape cShape = new CollisionShape(mesh);
		if(mesh.getClass() == Sphere.class) NCreateSphere(cShape, mass, pos, ((Sphere)mesh).getRadius());
		if(mesh.getClass() == Box.class)
		{
			Box box = (Box)mesh;
			NCreateBox(cShape, mass, pos, box.getWidth(), box.getHeight(), box.getDepth());
		}
		if(cShape.id == Integer.MAX_VALUE) throw new UnsupportedOperationException();
		return cShape;
	}
	
	protected void finalize() throws Throwable
	{
		destructor();
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
