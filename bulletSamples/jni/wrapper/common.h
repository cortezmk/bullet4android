#include <map>
#include "btBulletDynamicsCommon.h"
#include "btObjects.h"
#include <jni.h>

extern "C"
{
	void btVector3ToJobject(JNIEnv* env, btVector3& vec, jobject& obj);
	void jobjectToBtVector3(JNIEnv* env, jobject& obj, btVector3& vec);
	void btQuaternionToJobject(JNIEnv* env, btQuaternion& quat, jobject& obj);
	void jobjectToBtQuaternion(JNIEnv* env, jobject& obj, btQuaternion& quat);
}

template<class T>
T* getObject(JNIEnv* env, jobject& obj)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, "id", "I");
	return (T*)btObjects::get(env->GetIntField(obj, id));
}

void setNamedObject(JNIEnv* env, jobject& obj, char* name, int value);

template<class T>
T* getNamedObject(JNIEnv* env, jobject& obj, char* name)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, name, "I");
	return (T*)btObjects::get(env->GetIntField(obj, id));
}

int getIdNamedObject(JNIEnv* env, jobject& obj, char* name);
