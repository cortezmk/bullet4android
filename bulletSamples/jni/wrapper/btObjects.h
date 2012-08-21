extern "C"
{
	class btObjects
	{
		public:
		static std::map<int, void*>* pointer_map;
		static int pointer_map_count;
		
		static int put(void* p);
		static void* get(int p);
	};

	template<class T>
	T* getObject(JNIEnv* env, jobject& obj)
	{
		jclass claz = env->GetObjectClass(obj);
		jfieldID id = env->GetFieldID(claz, "id", "I");
		return (T*)btObjects::get(env->GetIntField(obj, id));
	}

	void setNamedObject(JNIEnv* env, jobject& obj, char* name, int value)
	{
		jclass claz = env->GetObjectClass(obj);
		jfieldID id = env->GetFieldID(claz, name, "I");
		env->SetIntField(obj, id, value);
	}

	template<class T>
	T* getNamedObject(JNIEnv* env, jobject& obj, char* name)
	{
		jclass claz = env->GetObjectClass(obj);
		jfieldID id = env->GetFieldID(claz, name, "I");
		return (T*)btObjects::get(env->GetIntField(obj, id));
	}

	int getIdNamedObject(JNIEnv* env, jobject& obj, char* name)
	{
		jclass claz = env->GetObjectClass(obj);
		jfieldID id = env->GetFieldID(claz, name, "I");
		return env->GetIntField(obj, id);
	}
}
