#include <jni.h>
#include "common.h"
#include "DebugDrawer.h"

static btRigidBody* pickedBody = 0;
static bool use6Dof = false;
static btTypedConstraint* pickConstraint = 0;
static const btScalar mousePickClamping = 30.f;
static btVector3 oldPickingPos, hitPos;
static btScalar oldPickingDist;
static int mouseOldX, mouseOldY;

extern "C"
{
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NsetDebugDrawer( JNIEnv* env, jobject self )
	{
		DebugDrawer* dd = new DebugDrawer;
		dd->setDebugMode(btIDebugDraw::DBG_DrawConstraints+btIDebugDraw::DBG_DrawConstraintLimits);
		getObject<btDiscreteDynamicsWorld>(env, self)->setDebugDrawer(dd);
	}

	JNIEXPORT jint Java_org_bulletSamples_physics_DynamicsWorld_constructor( JNIEnv* env, jobject self )
	{
		btBroadphaseInterface* broadphase = new btDbvtBroadphase();
		setNamedObject(env, self, "idBrodaphase", broadphase);
        btDefaultCollisionConfiguration* collisionConfiguration = new btDefaultCollisionConfiguration();
        setNamedObject(env, self, "idConfiguration", collisionConfiguration);
        btCollisionDispatcher* dispatcher = new btCollisionDispatcher(collisionConfiguration);
        setNamedObject(env, self, "idDispatcher", dispatcher);
        btSequentialImpulseConstraintSolver* solver = new btSequentialImpulseConstraintSolver;
        setNamedObject(env, self, "idSolver", solver);
        btDiscreteDynamicsWorld* dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher,broadphase,solver,collisionConfiguration);
        dynamicsWorld->setGravity(btVector3(0,-10,0));
        btCollisionShape* groundShape = new btStaticPlaneShape(btVector3(0,1,0),1);
        setNamedObject(env, self, "idGShape", groundShape);
 
        btDefaultMotionState* groundMotionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,1),btVector3(0,-1,0)));
        setNamedObject(env, self, "idGMState", groundMotionState);
        btRigidBody::btRigidBodyConstructionInfo
            groundRigidBodyCI(0,groundMotionState,groundShape,btVector3(0,0,0));
        btRigidBody* groundRigidBody = new btRigidBody(groundRigidBodyCI);
        setNamedObject(env, self, "idGRbody", groundRigidBody);
        dynamicsWorld->addRigidBody(groundRigidBody);

        //set debug drawer
        DebugDrawer* dd = new DebugDrawer;
        dd->setDebugMode(btIDebugDraw::DBG_DrawConstraints+btIDebugDraw::DBG_DrawConstraintLimits);
        dynamicsWorld->setDebugDrawer(dd);

		return btObjects::put(dynamicsWorld);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_destructor( JNIEnv* env, jobject self )
	{
		removeNamedObject<btRigidBody>(env, self, "idGRbody");
		removeNamedObject<btDefaultMotionState>(env, self, "idGMState");
		removeNamedObject<btDiscreteDynamicsWorld>(env, self, "id");
		removeNamedObject<btCollisionShape>(env, self, "idGShape");
		removeNamedObject<btSequentialImpulseConstraintSolver>(env, self, "idSolver");
		removeNamedObject<btCollisionDispatcher>(env, self, "idDispatcher");
		removeNamedObject<btDefaultCollisionConfiguration>(env, self, "idConfiguration");
		removeNamedObject<btBroadphaseInterface>(env, self, "idBrodaphase");
		btDiscreteDynamicsWorld* dynamicsWorld = getObject<btDiscreteDynamicsWorld>(env, self);
		if(dynamicsWorld->getDebugDrawer())
		{
			delete dynamicsWorld->getDebugDrawer();
		}
		delete dynamicsWorld;
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
        dynamicsWorld->addRigidBody(fallRigidBody);
        putObject(env, collisionShape, fallRigidBody);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NsetGravity( JNIEnv* env, jobject self, jint id, jfloat x, jfloat y, jfloat z )
	{
		((btDiscreteDynamicsWorld*)btObjects::get(id))->setGravity(btVector3(x,y,z));
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NstepSimulation( JNIEnv* env, jobject self, jint idDynamicsWorld, jint timeElapsed )
	{
		((btDiscreteDynamicsWorld*)btObjects::get(idDynamicsWorld))->stepSimulation((float)timeElapsed/1000.f,200);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NpickObject( JNIEnv* env, jobject self, jint id, jobject jRayFrom, jobject jRayTo )
	{
		btDiscreteDynamicsWorld* dw = (btDiscreteDynamicsWorld*)btObjects::get(id);
		if(pickConstraint != 0)
		{
			dw->removeConstraint(pickConstraint);
			delete pickConstraint;
			pickConstraint = 0;
			pickedBody = 0;
		}
		btVector3 rayFrom, rayTo;
		jobjectToBtVector3(env, jRayFrom, rayFrom);
		jobjectToBtVector3(env, jRayTo, rayTo);
		btCollisionWorld::ClosestRayResultCallback rayCallback(rayFrom,rayTo);
		dw->rayTest(rayFrom,rayTo,rayCallback);
		if (rayCallback.hasHit())
		{
			btRigidBody* body = btRigidBody::upcast(rayCallback.m_collisionObject);
			if (body)
			{
				//other exclusions?
				if (!(body->isStaticObject() || body->isKinematicObject()))
				{
					pickedBody = body;
					pickedBody->setActivationState(DISABLE_DEACTIVATION);
					btVector3 pickPos = rayCallback.m_hitPointWorld;
					btVector3 localPivot = body->getCenterOfMassTransform().inverse() * pickPos;
					if (use6Dof)
					{
						btTransform tr;
						tr.setIdentity();
						tr.setOrigin(localPivot);
						btGeneric6DofConstraint* dof6 = new btGeneric6DofConstraint(*body, tr,false);
						dof6->setLinearLowerLimit(btVector3(0,0,0));
						dof6->setLinearUpperLimit(btVector3(0,0,0));
						dof6->setAngularLowerLimit(btVector3(0,0,0));
						dof6->setAngularUpperLimit(btVector3(0,0,0));

						dw->addConstraint(dof6);
						pickConstraint = dof6;

						dof6->setParam(BT_CONSTRAINT_STOP_CFM,0.8,0);
						dof6->setParam(BT_CONSTRAINT_STOP_CFM,0.8,1);
						dof6->setParam(BT_CONSTRAINT_STOP_CFM,0.8,2);
						dof6->setParam(BT_CONSTRAINT_STOP_CFM,0.8,3);
						dof6->setParam(BT_CONSTRAINT_STOP_CFM,0.8,4);
						dof6->setParam(BT_CONSTRAINT_STOP_CFM,0.8,5);

						dof6->setParam(BT_CONSTRAINT_STOP_ERP,0.1,0);
						dof6->setParam(BT_CONSTRAINT_STOP_ERP,0.1,1);
						dof6->setParam(BT_CONSTRAINT_STOP_ERP,0.1,2);
						dof6->setParam(BT_CONSTRAINT_STOP_ERP,0.1,3);
						dof6->setParam(BT_CONSTRAINT_STOP_ERP,0.1,4);
						dof6->setParam(BT_CONSTRAINT_STOP_ERP,0.1,5);
					} else
					{
						btPoint2PointConstraint* p2p = new btPoint2PointConstraint(*body,localPivot);
						dw->addConstraint(p2p);
						pickConstraint = p2p;
						p2p->m_setting.m_impulseClamp = mousePickClamping;
						//very weak constraint for picking
						p2p->m_setting.m_tau = 0.001f;
					}
					use6Dof = !use6Dof;
					//save mouse position for dragging
					oldPickingPos = rayTo;
					hitPos = pickPos;
					oldPickingDist  = (pickPos-rayFrom).length();
				}
			}
		}
	}

	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NdropObject( JNIEnv* env, jobject self, jint id, jobject jRayFrom, jobject jRayTo )
	{
		btDiscreteDynamicsWorld* dw = (btDiscreteDynamicsWorld*)btObjects::get(id);
		if(pickConstraint != 0)
		{
			dw->removeConstraint(pickConstraint);
			delete pickConstraint;
			pickConstraint = 0;
		}
		if(pickedBody != 0)
		{
			pickedBody->forceActivationState(ACTIVE_TAG);
			pickedBody->setDeactivationTime( 0.f );
			pickedBody = 0;
		}
	}

	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NdragObject( JNIEnv* env, jobject self, jint id, jobject jRayFrom, jobject jRayTo )
	{
		if(pickConstraint == 0) return;
		btDiscreteDynamicsWorld* dw = (btDiscreteDynamicsWorld*)btObjects::get(id);
		btVector3 rayFrom, rayTo;
		jobjectToBtVector3(env, jRayFrom, rayFrom);
		jobjectToBtVector3(env, jRayTo, rayTo);
		if (pickConstraint->getConstraintType() == D6_CONSTRAINT_TYPE)
		{
			btGeneric6DofConstraint* pickCon = static_cast<btGeneric6DofConstraint*>(pickConstraint);
			if (pickCon)
			{
				btVector3 newRayTo = rayTo;
				btVector3 oldPivotInB = pickCon->getFrameOffsetA().getOrigin();
				btVector3 newPivotB;
				btVector3 dir = newRayTo-rayFrom;
				dir.normalize();
				dir *= oldPickingDist;
				newPivotB = rayFrom + dir;
				pickCon->getFrameOffsetA().setOrigin(newPivotB);
			}

		} else
		{
			btPoint2PointConstraint* pickCon = static_cast<btPoint2PointConstraint*>(pickConstraint);
			if (pickCon)
			{
				btVector3 newRayTo = rayTo;
				btVector3 oldPivotInB = pickCon->getPivotInB();
				btVector3 newPivotB;
				btVector3 dir = newRayTo-rayFrom;
				dir.normalize();
				dir *= oldPickingDist;
				newPivotB = rayFrom + dir;
				pickCon->setPivotB(newPivotB);
			}
		}
	}

	JNIEXPORT void Java_org_bulletSamples_physics_DynamicsWorld_NdrawDebug( JNIEnv* env, jobject self )
	{
		getObject<btDiscreteDynamicsWorld>(env, self)->debugDrawWorld();
	}
}
