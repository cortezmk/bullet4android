#include <jni.h>
#include "common.h"
//#include <BulletDynamics/btGeneric6DofSpringConstraint.h>

extern "C"
{
	JNIEXPORT jint Java_org_bulletSamples_physics_Spring_constructor( JNIEnv* env, jobject self, jint dynamicsWorld, jint irb1, jint irb2, jobject frameA, jobject frameB, jboolean linear )
	{
		btRigidBody* rb1 = (btRigidBody*)btObjects::get(irb1);
		btRigidBody* rb2 = (btRigidBody*)btObjects::get(irb2);
		btVector3 vA, vB;
		jobjectToBtVector3(env, frameA, vA);
		jobjectToBtVector3(env, frameB, vB);
		btTransform frameInA = btTransform::getIdentity();
		frameInA.setOrigin(vA);
		btTransform frameInB = btTransform::getIdentity();
		frameInB.setOrigin(vB);
		btGeneric6DofSpringConstraint* spring = new btGeneric6DofSpringConstraint(*rb1, *rb2, frameInA, frameInB, linear);
		((btDiscreteDynamicsWorld*)btObjects::get(dynamicsWorld))->addConstraint(spring, true);
		spring->setDbgDrawSize(btScalar(5.f));
		return btObjects::put(spring);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_destructor( JNIEnv* env, jobject self )
	{
		removeNamedObject<btGeneric6DofSpringConstraint>(env, self, "id");
	}

	JNIEXPORT void Java_org_bulletSamples_physics_Spring_Nenable( JNIEnv* env, jobject self, jint id, jint index, jboolean enable)
	{
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->enableSpring(1, enable);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetDamping( JNIEnv* env, jobject self, jint id, jint index, float damping)
	{
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setDamping(1, damping);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetStiffness( JNIEnv* env, jobject self, jint id, jint index, float stiffness)
	{
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setStiffness(1, stiffness);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetEquilibriumPoint( JNIEnv* env, jobject self, jint id)
	{
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setEquilibriumPoint();
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetLinearUpperLimit( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		btVector3 btVec;
		jobjectToBtVector3(env, vec, btVec);
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setLinearUpperLimit(btVec);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetLinearLowerLimit( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		btVector3 btVec;
		jobjectToBtVector3(env, vec, btVec);
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setLinearLowerLimit(btVec);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetAngularUpperLimit( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		btVector3 btVec;
		jobjectToBtVector3(env, vec, btVec);
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setAngularUpperLimit(btVec);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_Spring_NsetAngularLowerLimit( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		btVector3 btVec;
		jobjectToBtVector3(env, vec, btVec);
		((btGeneric6DofSpringConstraint*)btObjects::get(id))->setAngularLowerLimit(btVec);
	}
}
