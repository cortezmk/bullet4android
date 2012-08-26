#include <jni.h>
#include "common.h"

extern "C"
{
	JNIEXPORT jint Java_org_bulletSamples_physics_UniversalConstraint_constructor( JNIEnv* env, jobject self, jint dynamicsWorld, jint irb1, jint irb2, jobject anchor, jobject axis1, jobject axis2 )
	{
		btRigidBody* rb1 = (btRigidBody*)btObjects::get(irb1);
		btRigidBody* rb2 = (btRigidBody*)btObjects::get(irb2);
		btVector3 vA, vA1, vA2;
		jobjectToBtVector3(env, anchor, vA);
		jobjectToBtVector3(env, axis1, vA1);
		jobjectToBtVector3(env, axis2, vA2);
		btUniversalConstraint* constraint = new btUniversalConstraint(*rb1, *rb2, vA, vA1, vA2);
		((btDiscreteDynamicsWorld*)btObjects::get(dynamicsWorld))->addConstraint(constraint, true);
		return btObjects::put(constraint);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_UniversalConstraint_destructor( JNIEnv* env, jobject self )
	{
		removeNamedObject<btUniversalConstraint>(env, self, "id");
	}

	JNIEXPORT void Java_org_bulletSamples_physics_UniversalConstraint_NsetUpperLimit( JNIEnv* env, jobject self, jint id, jfloat ang1max, jfloat ang2max)
	{
		((btUniversalConstraint*)btObjects::get(id))->setUpperLimit(ang1max, ang2max);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_UniversalConstraint_NsetLowerLimit( JNIEnv* env, jobject self, jint id, jfloat ang1min, jfloat ang2min)
	{
		((btUniversalConstraint*)btObjects::get(id))->setLowerLimit(ang1min, ang2min);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_UniversalConstraint_NsetAxis( JNIEnv* env, jobject self, jint id, jobject axis1, jobject axis2)
	{
		//btVector3 vA1, vA2;
		//jobjectToBtVector3(env, axis1, vA1);
		//jobjectToBtVector3(env, axis2, vA2);
		//((btUniversalConstraint*)btObjects::get(id))->setAxis(vA1, vA2);
	}
}
