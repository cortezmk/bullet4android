#include <jni.h>
#include "common.h"

extern "C"
{
	JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved)
	{
		return JNI_VERSION_1_2;
	}
	
	void btVector3ToJobject(JNIEnv* env, const btVector3& vec, jobject& obj)
	{
		jclass vector3 = env->GetObjectClass(obj);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z;
		env->SetFloatField(obj, xf, vec.getX());
		env->SetFloatField(obj, yf, vec.getY());
		env->SetFloatField(obj, zf, vec.getZ());
	}
	
	jobject jVector3(JNIEnv* env, const btVector3& vec)
	{
		jclass claz = env->FindClass("org/bulletSamples/geometry/Vector3");
		jmethodID cid = env->GetMethodID(claz, "<init>", "()V");
		jobject obj = env->NewObject(claz, cid);
		jclass vector3 = env->GetObjectClass(obj);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z;
		env->SetFloatField(obj, xf, vec.getX());
		env->SetFloatField(obj, yf, vec.getY());
		env->SetFloatField(obj, zf, vec.getZ());
		return obj;
	}

	void jobjectToBtVector3(JNIEnv* env, jobject& obj, btVector3& vec)
	{
		jclass vector3 = env->GetObjectClass(obj);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z;
		x = env->GetFloatField(obj, xf);
		y = env->GetFloatField(obj, yf);
		z = env->GetFloatField(obj, zf);
		vec = btVector3(x, y, z);
	}
	
	void btQuaternionToJobject(JNIEnv* env, btQuaternion& quat, jobject& obj)
	{
		jclass quaternion = env->GetObjectClass(obj);
		jfieldID v3f = env->GetFieldID(quaternion, "axis", "Lorg/bulletSamples/geometry/Vector3;");
		jfieldID af = env->GetFieldID(quaternion, "angle", "F");
		jobject axis = env->GetObjectField(obj, v3f);
		jclass vector3 = env->GetObjectClass(axis);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		env->SetFloatField(axis, xf, quat.getAxis().getX());
		env->SetFloatField(axis, yf, quat.getAxis().getY());
		env->SetFloatField(axis, zf, quat.getAxis().getZ());
		env->SetFloatField(obj, af, quat.getAngle());
	}
	
	void jobjectToBtQuaternion(JNIEnv* env, jobject& obj, btQuaternion& quat)
	{
		jclass quaternion = env->GetObjectClass(obj);
		jfieldID v3f = env->GetFieldID(quaternion, "axis", "Lorg/bulletSamples/geometry/Vector3;");
		jfieldID af = env->GetFieldID(quaternion, "angle", "F");
		jobject axis = env->GetObjectField(obj, v3f);
		jclass vector3 = env->GetObjectClass(axis);
		jfieldID xf = env->GetFieldID(vector3, "x", "F");
		jfieldID yf = env->GetFieldID(vector3, "y", "F");
		jfieldID zf = env->GetFieldID(vector3, "z", "F");
		float x, y, z, a;
		x = env->GetFloatField(axis, xf);
		y = env->GetFloatField(axis, yf);
		z = env->GetFloatField(axis, zf);
		a = env->GetFloatField(obj, af);
		quat = btQuaternion(btVector3(x, y, z), a);
	}
}

void setNamedObject(JNIEnv* env, jobject& obj, char* name, int value)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, name, "I");
	env->SetIntField(obj, id, value);
}

int getIdNamedObject(JNIEnv* env, jobject& obj, char* name)
{
	jclass claz = env->GetObjectClass(obj);
	jfieldID id = env->GetFieldID(claz, name, "I");
	return env->GetIntField(obj, id);
}
