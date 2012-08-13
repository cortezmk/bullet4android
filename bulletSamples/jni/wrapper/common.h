#include <map>
#include "btBulletDynamicsCommon.h"
#include "btObjects.h"

extern "C"
{
	void btVector3ToJobject(JNIEnv* env, btVector3& vec, jobject& obj);
	void jobjectToBtVector3(JNIEnv* env, jobject& obj, btVector3& vec);
	void btQuaternionToJobject(JNIEnv* env, btQuaternion& quat, jobject& obj);
	void jobjectToBtQuaternion(JNIEnv* env, jobject& obj, btQuaternion& quat);
}