#include <jni.h>
#include "common.h"

extern "C"
{
	JNIEXPORT int Java_org_bulletSamples_physics_DynamicsWorld_Bla0( JNIEnv* env, jobject self)
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

	JNIEXPORT int Java_org_bulletSamples_physics_DynamicsWorld_BlaCreateRigidBody( JNIEnv* env, jobject self, jint idDynamicsWorld, jobject position )
	{
		btDiscreteDynamicsWorld* dynamicsWorld = ((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld));
		btCollisionShape* fallShape = new btBoxShape(btVector3(.5f, .5f, .5f));
		btVector3 btPosition;
		jobjectToBtVector3(env, position, btPosition);
		btDefaultMotionState* fallMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btPosition));
        btScalar mass = 1;
        btVector3 fallInertia(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI(mass,fallMotionState,fallShape,fallInertia);
        btRigidBody* fallRigidBody = new btRigidBody(fallRigidBodyCI);
        dynamicsWorld->addRigidBody(fallRigidBody);
		return btObjects::put(fallRigidBody);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_BlaStepSimulation( JNIEnv* env, jobject self, jint timeElapsed, jint idDynamicsWorld )
	{
		((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld))->stepSimulation((float)timeElapsed/1000.f,50);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_BoxCollisionShape_BlaGetTransform( JNIEnv* env, jobject self, jint idRigidBody, jobject vec, jobject quat )
	{
		btTransform trans;
		((btRigidBody*)btObjects::get(idRigidBody))->getMotionState()->getWorldTransform(trans);
		btQuaternion q;
		q = trans.getRotation();
		btVector3ToJobject(env, trans.getOrigin(), vec);
		btQuaternionToJobject(env, q, quat);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_BoxCollisionShape_Bla( JNIEnv* env, jobject self, jfloatArray array)
	{
        btBroadphaseInterface* broadphase = new btDbvtBroadphase();
        btDefaultCollisionConfiguration* collisionConfiguration = new btDefaultCollisionConfiguration();
        btCollisionDispatcher* dispatcher = new btCollisionDispatcher(collisionConfiguration);
        btSequentialImpulseConstraintSolver* solver = new btSequentialImpulseConstraintSolver;
        btDiscreteDynamicsWorld* dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher,broadphase,solver,collisionConfiguration);
        dynamicsWorld->setGravity(btVector3(0,-10,0));

        btCollisionShape* groundShape = new btStaticPlaneShape(btVector3(0,1,0),1);
        btCollisionShape* fallShape = new btBoxShape(btVector3(.5f, .5f, .5f));
		btCollisionShape* fallShape2 = new btBoxShape(btVector3(.5f, .5f, .5f));

        btDefaultMotionState* groundMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btVector3(0,-1,0)));
        btRigidBody::btRigidBodyConstructionInfo
                groundRigidBodyCI(0,groundMotionState,groundShape,btVector3(0,0,0));
        btRigidBody* groundRigidBody = new btRigidBody(groundRigidBodyCI);
        dynamicsWorld->addRigidBody(groundRigidBody);

        btDefaultMotionState* fallMotionState =
                new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btVector3(0,1,0)));
        btScalar mass = 1;
        btVector3 fallInertia(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI(mass,fallMotionState,fallShape,fallInertia);
        btRigidBody* fallRigidBody = new btRigidBody(fallRigidBodyCI);
        dynamicsWorld->addRigidBody(fallRigidBody);

		btDefaultMotionState* fallMotionState2 =
                new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btVector3(.4f,10,0)));
		btVector3 fallInertia2(0,0,0);
        fallShape->calculateLocalInertia(mass,fallInertia2);
        btRigidBody::btRigidBodyConstructionInfo fallRigidBodyCI2(mass,fallMotionState2,fallShape2,fallInertia2);
        btRigidBody* fallRigidBody2 = new btRigidBody(fallRigidBodyCI2);
        dynamicsWorld->addRigidBody(fallRigidBody2);

		int idDynamicsWorld = btObjects::put(dynamicsWorld);
		int idFallRigidBody2 = btObjects::put(fallRigidBody2);

		float buf[400];
		for (int i=0 ; i<200 ; i++) {
			((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld))->stepSimulation(1/60.f,50);
			btTransform trans;
			((btRigidBody*)btObjects::get(idFallRigidBody2))->getMotionState()->getWorldTransform(trans);
			buf[i*2] = (float)trans.getOrigin().getY();
			buf[i*2+1] = (float)trans.getOrigin().getX();
        }
		env->SetFloatArrayRegion(array, 0, 400, buf);

        dynamicsWorld->removeRigidBody(fallRigidBody);
        delete fallRigidBody->getMotionState();
        delete fallRigidBody;

		//delete fallRigidBody2->getMotionState();
        //delete fallRigidBody2;

        dynamicsWorld->removeRigidBody(groundRigidBody);
        delete groundRigidBody->getMotionState();
        delete groundRigidBody;
        delete fallShape;
        delete groundShape;
        delete dynamicsWorld;
        delete solver;
        delete collisionConfiguration;
        delete dispatcher;
        delete broadphase;
	}
	//JNIEXPORT jobject Java_org_bulletSamples_physics_BoxCollisionShape_NgetRotation
}
