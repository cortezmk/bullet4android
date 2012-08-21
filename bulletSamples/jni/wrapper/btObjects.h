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
}

