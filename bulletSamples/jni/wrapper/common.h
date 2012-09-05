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
	jobject jVector3(JNIEnv* env, const btVector3& vec);
}

template<class T>
T* getObject(JNIEnv* env, jobject& obj)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, "id", "I");
	return (T*)btObjects::get(env->GetIntField(obj, id));
}

template<class T>
void putObject(JNIEnv* env, jobject& obj, T* value)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, "id", "I");
	env->SetIntField(obj, id, btObjects::put(value));
}

template<class T>
void setNamedObject(JNIEnv* env, jobject& obj, char* name, T* value)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, name, "I");
	env->SetIntField(obj, id, btObjects::put(value));
}

template<class T>
T* getNamedObject(JNIEnv* env, jobject& obj, char* name)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, name, "I");
	return (T*)btObjects::get(env->GetIntField(obj, id));
}

int getIdNamedObject(JNIEnv* env, jobject& obj, char* name);

template<class T>
void removeNamedObject(JNIEnv* env, jobject& obj, char* name)
{
	T* rev = getNamedObject<T>(env, obj, name);
	int id = getIdNamedObject(env, obj, name);
	delete rev;
	btObjects::remove(id);
}

template<class T>
void addNamedObject(JNIEnv* env, jobject& obj, char* name, T value)
{
	setNamedObject(env, obj, name, btObjects::put(value));
}
