#include<map>
#include "btObjects.h"

std::map<int, void*>* btObjects::pointer_map = new std::map<int, void*>;
int btObjects::pointer_map_count = 0;

int btObjects::put(void* p)
{
	(*pointer_map)[pointer_map_count] = p;
	pointer_map_count++;
	return pointer_map_count - 1;
}

void* btObjects::get(int p)
{
	return (*pointer_map)[p];
}

void btObjects::remove(int p)
{
	(*pointer_map).erase((*pointer_map).find(p));
}
