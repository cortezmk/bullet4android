LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := bulletWrapper
LOCAL_SRC_FILES := wrapper/DynamicsWorld.cpp \
wrapper/common.cpp

include $(BUILD_SHARED_LIBRARY)