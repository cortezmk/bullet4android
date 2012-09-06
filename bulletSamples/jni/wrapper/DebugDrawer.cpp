#include "common.h"
#include "DebugDrawer.h"

JNIEnv* globalEnv = 0;
jobject jFrom, jTo, jFromColor, jToColor;

extern "C"
{
	JNIEXPORT void Java_org_bulletSamples_physics_DebugDrawer_renewEnv(JNIEnv* env, jclass claz)
	{
		globalEnv = env;
	}

	JNIEXPORT void Java_org_bulletSamples_physics_DebugDrawer_init(JNIEnv* env, jclass claz)
	{
	}
}

void setNamedVector3(JNIEnv* env, jclass claz, char* name, const btVector3 vec)
{
	jfieldID id = env->GetStaticFieldID(claz, name, "Lorg/bulletSamples/geometry/Vector3;");
	jobject obj = env->GetStaticObjectField(claz, id);
	btVector3ToJobject(env, vec, obj);
	env->DeleteLocalRef(obj);
}

DebugDrawer::DebugDrawer():m_debugMode(0)
{
}

/*void DebugDrawer::drawLine(const btVector3& from,const btVector3& to,const btVector3& fromColor, const btVector3& toColor)
{
	jclass claz = globalEnv->FindClass("org/bulletSamples/physics/DebugDrawer");
	jmethodID mid = globalEnv->GetStaticMethodID(claz, "drawLine", "(Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;)V");
	jobject jFrom = jVector3(globalEnv, from);
	jobject jTo = jVector3(globalEnv, to);
	jobject jFromColor = jVector3(globalEnv, fromColor);
	jobject jToColor = jVector3(globalEnv, toColor);
	jobject gFrom = globalEnv->NewGlobalRef(jFrom);
	jobject gTo = globalEnv->NewGlobalRef(jTo);
	jobject gFromColor = globalEnv->NewGlobalRef(jFromColor);
	jobject gToColor = globalEnv->NewGlobalRef(jToColor);
	globalEnv->DeleteLocalRef(jFrom);
	globalEnv->DeleteLocalRef(jTo);
	globalEnv->DeleteLocalRef(jFromColor);
	globalEnv->DeleteLocalRef(jToColor);
	globalEnv->DeleteLocalRef(claz);
	globalEnv->CallStaticVoidMethod(claz, mid, gFrom, gTo, gFromColor, gToColor);
	globalEnv->DeleteGlobalRef(gFrom);
	globalEnv->DeleteGlobalRef(gTo);
	globalEnv->DeleteGlobalRef(gFromColor);
	globalEnv->DeleteGlobalRef(gToColor);
}*/

void DebugDrawer::drawLine(const btVector3& from,const btVector3& to,const btVector3& fromColor, const btVector3& toColor)
{
}

void DebugDrawer::drawLine(const btVector3& from,const btVector3& to,const btVector3& color)
{
	jclass claz = globalEnv->FindClass("org/bulletSamples/physics/DebugDrawer");
	jmethodID mid = globalEnv->GetStaticMethodID(claz, "drawLine", "()V");
	setNamedVector3(globalEnv, claz, "dummy1", from);
	setNamedVector3(globalEnv, claz, "dummy2", to);
	setNamedVector3(globalEnv, claz, "dummy3", color);
	setNamedVector3(globalEnv, claz, "dummy4", color);
	globalEnv->CallStaticVoidMethod(claz, mid);
}

void DebugDrawer::drawSphere (const btVector3& p, btScalar radius, const btVector3& color)
{
	/*jclass claz = globalEnv->FindClass("org/bulletSamples/physics/DebugDrawer");
	jmethodID mid = globalEnv->GetStaticMethodID(claz, "drawSphere", "(Lorg/bulletSamples/geometry/Vector3;FLorg/bulletSamples/geometry/Vector3;)V");
	globalEnv->CallStaticVoidMethod(claz, mid, jVector3(globalEnv, p), radius, jVector3(globalEnv, color));*/
}

void DebugDrawer::drawBox (const btVector3& boxMin, const btVector3& boxMax, const btVector3& color, btScalar alpha)
{

}

void DebugDrawer::drawTriangle(const btVector3& a,const btVector3& b,const btVector3& c,const btVector3& color,btScalar alpha)
{
	/*jclass claz = globalEnv->FindClass("org/bulletSamples/physics/DebugDrawer");
	jmethodID mid = globalEnv->GetStaticMethodID(claz, "drawTriangle", "(Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;F)V");
	globalEnv->CallStaticVoidMethod(claz, mid, jVector3(globalEnv, a), jVector3(globalEnv, b), jVector3(globalEnv, c), jVector3(globalEnv, color), alpha);*/
}

void DebugDrawer::setDebugMode(int debugMode)
{
	m_debugMode = debugMode;
}

void DebugDrawer::draw3dText(const btVector3& location,const char* textString)
{
}

void DebugDrawer::reportErrorWarning(const char* warningString)
{
}

void DebugDrawer::drawContactPoint(const btVector3& pointOnB,const btVector3& normalOnB,btScalar distance,int lifeTime,const btVector3& color)
{
	/*jclass claz = globalEnv->FindClass("org/bulletSamples/physics/DebugDrawer");
	jmethodID mid = globalEnv->GetStaticMethodID(claz, "drawTriangle", "(Lorg/bulletSamples/geometry/Vector3;Lorg/bulletSamples/geometry/Vector3;FILorg/bulletSamples/geometry/Vector3;)V");
	globalEnv->CallStaticVoidMethod(claz, mid, jVector3(globalEnv, pointOnB), jVector3(globalEnv, normalOnB), distance, lifeTime, jVector3(globalEnv, color));*/
}



