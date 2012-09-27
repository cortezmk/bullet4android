#include "common.h"

extern "C"
{
	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_destructor( JNIEnv* env, jobject self, jint id, jint idWorld )
	{
		btCollisionObject* obj = ((btRigidBody*)btObjects::get(id));
		((btDiscreteDynamicsWorld*)btObjects::get(idWorld))->removeCollisionObject(obj);
		btObjects::remove(id);
		//removeNamedObject<btDefaultMotionState>(env, self, "idMState");
		//removeNamedObject<btCollisionShape>(env, self, "idShape");
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
		jfieldID v3f = env->GetFieldID(quaternion, "axis", "Lorg/bulletSamples/geometry/Vector3;");
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
		jfieldID v3f = env->GetFieldID(quaternion, "axis", "Lorg/bulletSamples/geometry/Vector3;");
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

	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetRestitution( JNIEnv* env, jobject self, jint id, jfloat value)
	{
		((btRigidBody*)btObjects::get(id))->setRestitution(value);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetFriction( JNIEnv* env, jobject self, jint id, jfloat value)
	{
		((btRigidBody*)btObjects::get(id))->setFriction(value);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetLinearVelocity( JNIEnv* env, jobject self, jint id, jobject value)
	{
		btVector3 vec;
		jobjectToBtVector3(env, value, vec);
		((btRigidBody*)btObjects::get(id))->setLinearVelocity(vec);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NsetAngularVelocity( JNIEnv* env, jobject self, jint id, jobject value)
	{
		btVector3 vec;
		jobjectToBtVector3(env, value, vec);
		((btRigidBody*)btObjects::get(id))->setAngularVelocity(vec);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NgetLinearVelocity( JNIEnv* env, jobject self, jint id, jobject value)
	{
		btVector3 vec = ((btRigidBody*)btObjects::get(id))->getLinearVelocity();
		btVector3ToJobject(env, vec, value);
	}

	JNIEXPORT void Java_org_bulletSamples_physics_CollisionShape_NgetAngularVelocity( JNIEnv* env, jobject self, jint id, jobject value)
	{
		btVector3 vec = ((btRigidBody*)btObjects::get(id))->getAngularVelocity();
		btVector3ToJobject(env, vec, value);
	}

	JNIEXPORT float Java_org_bulletSamples_physics_CollisionShape_NgetMomentOfInertia( JNIEnv* env, jobject self, jint id, jobject vector )
	{
		btMatrix3x3 tensor = ((btRigidBody*)btObjects::get(id))->getInvInertiaTensorWorld();
		btVector3 n;
		jobjectToBtVector3(env, vector, n);
		return (n * tensor).dot(n);
	}
}
