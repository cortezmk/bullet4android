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
T* getObject(JNIEnv* env, jobject& obj);

void setNamedObject(JNIEnv* env, jobject& obj, char* name, int value);

template<class T>
T* getNamedObject(JNIEnv* env, jobject& obj, char* name);

int getIdNamedObject(JNIEnv* env, jobject& obj, char* name);
