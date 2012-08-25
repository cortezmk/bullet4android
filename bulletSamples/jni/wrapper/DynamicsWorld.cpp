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
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NCreateBox( JNIEnv* env, jobject self, jobject collisionShape, jfloat mass, jobject position, jfloat width, jfloat height, jfloat depth )
	{
		btDiscreteDynamicsWorld* dynamicsWorld = getObject<btDiscreteDynamicsWorld>(env, self);
		btCollisionShape* fallShape = new btBoxShape(btVector3(width/2.0f, height/2.0f, depth/2.0f));
		setNamedObject(env, collisionShape, "idShape", fallShape);
		btVector3 btPosition;
		jobjectToBtVector3(env, position, btPosition);
		btDefaultMotionState* fallMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btPosition));
		setNamedObject(env, collisionShape, "idMState", fallMotionState);
        btVector3 fallInertia(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI(mass,fallMotionState,fallShape,fallInertia);
        btRigidBody* fallRigidBody = new btRigidBody(fallRigidBodyCI);
        dynamicsWorld->addRigidBody(fallRigidBody);
        putObject(env, collisionShape, fallRigidBody);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NCreateSphere( JNIEnv* env, jobject self, jobject collisionShape, jfloat mass, jobject position, jfloat radius )
	{
		btDiscreteDynamicsWorld* dynamicsWorld = getObject<btDiscreteDynamicsWorld>(env, self);
		btCollisionShape* fallShape = new btSphereShape(radius);
		setNamedObject(env, collisionShape, "idShape", fallShape);
		btVector3 btPosition;
		jobjectToBtVector3(env, position, btPosition);
		btDefaultMotionState* fallMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btPosition));
		setNamedObject(env, collisionShape, "idMState", fallMotionState);
        btVector3 fallInertia(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI(mass,fallMotionState,fallShape,fallInertia);
        btRigidBody* fallRigidBody = new btRigidBody(fallRigidBodyCI);
		fallRigidBody->setRestitution(1);
        dynamicsWorld->addRigidBody(fallRigidBody);
        putObject(env, collisionShape, fallRigidBody);
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
