#include <jni.h>
#include "common.h"

extern "C"
{
	JNIEXPORT jint Java_org_bulletSamples_physics_DynamicsWorld_constructor( JNIEnv* env, jobject self )
	{
		btBroadphaseInterface* broadphase = new btDbvtBroadphase();
        btDefaultCollisionConfiguration* collisionConfiguration = new btDefaultCollisionConfiguration();
        btCollisionDispatcher* dispatcher = new btCollisionDispatcher(collisionConfiguration);
        btSequentialImpulseConstraintSolver* solver = new btSequentialImpulseConstraintSolver;
        btDiscreteDynamicsWorld* dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher,broadphase,solver,collisionConfiguration);
        dynamicsWorld->setGravity(btVector3(0,-10,0));
        btCollisionShape* groundShape = new btStaticPlaneShape(btVector3(0,1,0),1);
 
        btDefaultMotionState* groundMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btVector3(0,-1,0)));
        btRigidBody::btRigidBodyConstructionInfo
            groundRigidBodyCI(0,groundMotionState,groundShape,btVector3(0,0,0));
        btRigidBody* groundRigidBody = new btRigidBody(groundRigidBodyCI);
        dynamicsWorld->addRigidBody(groundRigidBody);
		return btObjects::put(dynamicsWorld);
	}
	
	JNIEXPORT int Java_org_bulletSamples_physics_DynamicsWorld_NCreateBox( JNIEnv* env, jobject self, jint idDynamicsWorld, jfloat mass, jobject position )
	{
		btDiscreteDynamicsWorld* dynamicsWorld = ((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld));
		btCollisionShape* fallShape = new btBoxShape(btVector3(.5f, .5f, .5f));
		btVector3 btPosition;
		jobjectToBtVector3(env, position, btPosition);
		btDefaultMotionState* fallMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btPosition));
        btVector3 fallInertia(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI(mass,fallMotionState,fallShape,fallInertia);
        btRigidBody* fallRigidBody = new btRigidBody(fallRigidBodyCI);
        dynamicsWorld->addRigidBody(fallRigidBody);
		return btObjects::put(fallRigidBody);
	}
	
	JNIEXPORT int Java_org_bulletSamples_physics_DynamicsWorld_NCreateSphere( JNIEnv* env, jobject self, jint idDynamicsWorld, jfloat mass, jobject position )
	{
		btDiscreteDynamicsWorld* dynamicsWorld = ((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld));
		btCollisionShape* fallShape = new btSphereShape(.5f);
		btVector3 btPosition;
		jobjectToBtVector3(env, position, btPosition);
		btDefaultMotionState* fallMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btPosition));
        btVector3 fallInertia(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI(mass,fallMotionState,fallShape,fallInertia);
        btRigidBody* fallRigidBody = new btRigidBody(fallRigidBodyCI);
		fallRigidBody->setRestitution(1);
        dynamicsWorld->addRigidBody(fallRigidBody);
		return btObjects::put(fallRigidBody);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_destructor( JNIEnv* env, jobject self )
	{
	
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NsetGravity( JNIEnv* env, jobject self, jint id, jfloat x, jfloat y, jfloat z )
	{
		((btDiscreteDynamicsWorld*)btObjects::get(id))->setGravity(btVector3(x,y,z));
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NstepSimulation( JNIEnv* env, jobject self, jint idDynamicsWorld, jint timeElapsed )
	{
		((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld))->stepSimulation((float)timeElapsed/1000.f,200);
	}
}
