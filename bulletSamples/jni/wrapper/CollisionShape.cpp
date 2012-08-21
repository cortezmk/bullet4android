#include "common.h"

extern "C"
{
	JNIEXPORT jint Java_org_bulletSamples_physics_CollisionShape_constructor( JNIEnv* env, jobject self )
	{
		btCollisionShape* shape = new btBoxShape(btVector3(.5f, .5f, .5f));
		btScalar mass = 1;
		btVector3 inertia(0,0,0);
		btDefaultMotionState* motionState = new btDefaultMotionState(btTransform(btQuaternion(0,0,0,0),btVector3(0,2,-15)));
		btVector3 fallInertia(0,0,0);
		shape->calculateLocalInertia(mass,fallInertia);
		btRigidBody::btRigidBodyConstructionInfo rigidBodyCI(mass,motionState,shape,inertia);
        btRigidBody* rigidBody = new btRigidBody(rigidBodyCI);
		//rigidBody->setActivationState(ISLAND_SLEEPING);
		return btObjects::put(rigidBody);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_destructor( JNIEnv* env, jobject self )
	{
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetMass( JNIEnv* env, jobject self, jint idRigidBody, jfloat mass )
	{
		btVector3 fallInertia(0,0,0);
		//((btRigidBody*)btObjects::get(idRigidBody))->getCollisionShape()->calculateLocalInertia(mass, fallInertia)
		((btRigidBody*)btObjects::get(idRigidBody))->setMassProps(mass, fallInertia);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NgetTransform( JNIEnv* env, jobject self, jint idRigidBody, jobject vec, jobject quat )
	{
		btTransform trans;
		((btRigidBody*)btObjects::get(idRigidBody))->getMotionState()->getWorldTransform(trans);
		btQuaternion q;
		q = trans.getRotation();
		btVector3ToJobject(env, trans.getOrigin(), vec);
		btQuaternionToJobject(env, q, quat);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NgetTranslation( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		jclass vector3 = env->GetObjectClass(vec);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		btTransform trans;
		((btRigidBody*)btObjects::get(id))->getMotionState()->getWorldTransform(trans);
		env->SetFloatField(vec, xf, trans.getOrigin().getX());
		env->SetFloatField(vec, yf, trans.getOrigin().getY());
		env->SetFloatField(vec, zf, trans.getOrigin().getZ());
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NgetRotation( JNIEnv* env, jobject self, jint id, jobject quat)
	{
		jclass quaternion = env->GetObjectClass(quat);
		jfieldID v3f = env->GetFieldID(quaternion, "axis", "Lorg/bulletSamples/Vector3;");
		jfieldID af = env->GetFieldID(quaternion, "angle", "F");
		jobject axis = env->GetObjectField(quat, v3f);
		jclass vector3 = env->GetObjectClass(axis);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		btTransform trans;
		((btRigidBody*)btObjects::get(id))->getMotionState()->getWorldTransform(trans);
		btQuaternion rotation = trans.getRotation();
		env->SetFloatField(axis, xf, rotation.getAxis().getX());
		env->SetFloatField(axis, yf, rotation.getAxis().getY());
		env->SetFloatField(axis, zf, rotation.getAxis().getZ());
		env->SetFloatField(quat, af, rotation.getAngle());
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetTranslation( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		jclass vector3 = env->GetObjectClass(vec);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z;
		btTransform trans = ((btRigidBody*)btObjects::get(id))->getCenterOfMassTransform();
		trans.setIdentity();
		x = env->GetFloatField(vec, xf);
		y = env->GetFloatField(vec, yf);
		z = env->GetFloatField(vec, zf);
		trans.setOrigin(btVector3(x, y, z));
		((btRigidBody*)btObjects::get(id))->setCenterOfMassTransform(trans);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetRotation( JNIEnv* env, jobject self, jint id, jobject quat)
	{
		jclass quaternion = env->GetObjectClass(quat);
		jfieldID v3f = env->GetFieldID(quaternion, "axis", "Lorg/bulletSamples/Vector3;");
		jfieldID af = env->GetFieldID(quaternion, "angle", "F");
		jobject axis = env->GetObjectField(quat, v3f);
		jclass vector3 = env->GetObjectClass(axis);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		btTransform trans;
		((btRigidBody*)btObjects::get(id))->getMotionState()->getWorldTransform(trans);
		float x, y, z, a;
		x = env->GetFloatField(axis, xf);
		y = env->GetFloatField(axis, yf);
		z = env->GetFloatField(axis, zf);
		a = env->GetFloatField(quat, af);
		trans.setRotation(btQuaternion(btVector3(x, y, z), a));
		((btRigidBody*)btObjects::get(id))->getMotionState()->setWorldTransform(trans);
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NapplyCentralForce( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		jclass vector3 = env->GetObjectClass(vec);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z;
		x = env->GetFloatField(vec, xf);
		y = env->GetFloatField(vec, yf);
		z = env->GetFloatField(vec, zf);
		((btRigidBody*)btObjects::get(id))->applyCentralForce(btVector3(x, y, z));
	}
	
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NapplyTorque( JNIEnv* env, jobject self, jint id, jobject vec)
	{
		jclass vector3 = env->GetObjectClass(vec);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z;
		x = env->GetFloatField(vec, xf);
		y = env->GetFloatField(vec, yf);
		z = env->GetFloatField(vec, zf);
		((btRigidBody*)btObjects::get(id))->applyTorque(btVector3(x, y, z));
	}
}
