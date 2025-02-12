LOCAL_PATH:= $(call my-dir)
APP_ALLOW_MISSING_DEPS :=true
#NDK_LIBS_OUT :=../jniLibs/armeabi-v7a
##############DLibModule##################

include $(CLEAR_VARS)
MAINDIR:= $(LOCAL_PATH)
include $(MAINDIR)/Thirdparty/clapack/Android.mk
LOCAL_PATH := $(MAINDIR)

include $(CLEAR_VARS)
MAINDIR:= $(LOCAL_PATH)
LOCAL_MODULE:= lapack
LOCAL_SHORT_COMMANDS := true
LOCAL_STATIC_LIBRARIES := clapack blas f2c

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_C_INCLUDES)
LOCAL_EXPORT_LDLIBS := $(LOCAL_LDLIBS)
LOCAL_PATH := $(MAINDIR)
include $(BUILD_SHARED_LIBRARY)
LOCAL_C_INCLUDES+=$(LOCAL_PATH)
FILE_LIST:=$(wildcard $(LOCAL_PATH)/*.cpp)
######################################
OPENCV_MK_PATH := C:\Users\Pascal\AndroidstudioProjects\OpenCV-4.4.1-android-sdk\sdk\native\jni\OpenCV.mk
#############DLibModule##################
include $(CLEAR_VARS)
MAIN_DIR:=$(LOCAL_PATH)
OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")  
#try to load OpenCV.mk from default install location  
include  C:/opencv-3.3.0-android-sdk/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk
else  
include $(OPENCV_MK_PATH)  
endif 
LOCAL_PATH:=$(MAIN_DIR)
LOCAL_MODULE:=DLib

####################Source file section######################################
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/DBoW2/DLib/include/DUtils
FILE_LIST:=$(wildcard $(LOCAL_PATH)/Thirdparty/DBoW2/DLib/src/DUtils/*.cpp)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)

################BOOST#####################part
BOOST_VERSION      := 1_49
PROJECT_ROOT       := $(LOCAL_PATH)
BOOST_INCLUDE_PATH := $(PROJECT_ROOT)/Thirdparty/Boost/include/boost-1_49
BOOST_LIB_PATH     := $(PROJECT_ROOT)/Thirdparty/Boost/lib
LOCAL_C_INCLUDES+= $(BOOST_INCLUDE_PATH) 
LOCAL_LDLIBS    := -llog
# The order of these libraries is often important.
LOCAL_LDLIBS += -L$(BOOST_LIB_PATH)     
LOCAL_LDLIBS    +=-lz -llog -landroid -lEGL -lGLESv1_CM
LOCAL_CPPFLAGS := -std=c++14 -pthread -frtti -fexceptions
LOCAL_CPPFLAGS += -D__cplusplus=201103L

#LOCAL_EXPORT_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/DBoW2/DLib/include/DUtils
#LOCAL_EXPORT_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/DBoW2/DLib/include/DUtilsCV
LOCAL_EXPORT_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/DBoW2/DLib/include
LOCAL_EXPORT_C_INCLUDES+=$(BOOST_INCLUDE_PATH)
LOCAL_EXPORT_C_INCLUDES+=$(OPENCV_MK_PATH) #C:/opencv-3.3.0-android-sdk/OpenCV-android-sdk/sdk/native/jni/include
LOCAL_PATH:=$(MAIN_DIR)
include $(BUILD_SHARED_LIBRARY)
###############################################################

##############DBoW2 module#########################################
include $(CLEAR_VARS)
MAIN_DIR:=$(LOCAL_PATH)

OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")  
#try to load OpenCV.mk from default install location  
include  C:/opencv-3.3.0-android-sdk/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk
else  
include $(OPENCV_MK_PATH)  
endif 
LOCAL_PATH:=$(MAIN_DIR)

LOCAL_MODULE:=DBoW2
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/DBoW2/include/DBoW2
FILE_LIST:=$(wildcard $(LOCAL_PATH)/Thirdparty/DBoW2/src/*.cpp)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)
LOCAL_SHARED_LIBRARIES+=DLib
LOCAL_EXPORT_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/DBoW2/include
LOCAL_CPPFLAGS := -std=c++14 -pthread -frtti -fexceptions
LOCAL_CPPFLAGS += -D__cplusplus=201103L
LOCAL_PATH:=$(MAIN_DIR)
include $(BUILD_SHARED_LIBRARY)
###############################################################


###################G2O module##################################
include $(CLEAR_VARS)
MAIN_DIR:=$(LOCAL_PATH)
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/g2o/g2o/core
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/g2o/g2o/stuff
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/g2o/g2o/solvers
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/g2o/g2o/types
FILE_LIST:=$(wildcard $(LOCAL_PATH)/Thirdparty/g2o/g2o/core/*.cpp)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)

FILE_LIST:=$(wildcard $(LOCAL_PATH)/Thirdparty/g2o/g2o/solvers/*.cpp)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)

FILE_LIST:=$(wildcard $(LOCAL_PATH)/Thirdparty/g2o/g2o/stuff/*.cpp)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)

FILE_LIST:=$(wildcard $(LOCAL_PATH)/Thirdparty/g2o/g2o/types/*.cpp)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)

LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/eigen3
LOCAL_SHARED_LIBRARIES+=lapack
LOCAL_MODULE:=g2o
LOCAL_EXPORT_LDLIBS := $(LOCAL_LDLIBS)
LOCAL_EXPORT_C_INCLUDES+=LOCAL_C_INCLUDES
LOCAL_CPPFLAGS := -std=c++11 -pthread -frtti -fexceptions
LOCAL_CPPFLAGS += -D__cplusplus=201103L
LOCAL_PATH:=$(MAIN_DIR)
include $(BUILD_SHARED_LIBRARY)
############################################################

##############ORB_SLAM2 module##################################
include $(CLEAR_VARS)
MAIN_DIR:=$(LOCAL_PATH)
OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")  
#try to load OpenCV.mk from default install location  
include  C:/opencv-3.3.0-android-sdk/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk
else  
include $(OPENCV_MK_PATH)  
endif 
LOCAL_MODULE := ORB_SLAM2
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/eigen3
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/ORB_SLAM2/include
FILE_LIST:=$(wildcard $(LOCAL_PATH)/ORB_SLAM2/src/*.cc)
LOCAL_SRC_FILES+=$(FILE_LIST:$(LOCAL_PATH)/%=%)
LOCAL_CPP_EXTENSION := .cc
LOCAL_SHARED_LIBRARIES+=DBoW2
LOCAL_SHARED_LIBRARIES+=DLib
LOCAL_SHARED_LIBRARIES+=g2o
#LOCAL_SHARED_LIBRARIES+=pangolin
LOCAL_LDLIBS += -llog -landroid -lEGL -lGLESv1_CM 
LOCAL_EXPORT_C_INCLUDES+=$(LOCAL_PATH)/ORB_SLAM2/include
LOCAL_CPPFLAGS := -std=c++11 -pthread -frtti -fexceptions -ftemplate-backtrace-limit=0
LOCAL_CPPFLAGS += -D__cplusplus=201103L
LOCAL_PATH:=$(MAIN_DIR)
include $(BUILD_SHARED_LIBRARY)
############################################################

##############ORB_SLAM2 Execution module###############################
include $(CLEAR_VARS)
MAIN_DIR:=$(LOCAL_PATH)
OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")  
#try to load OpenCV.mk from default install location  
include  C:/opencv-3.3.0-android-sdk/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk
else  
include $(OPENCV_MK_PATH)  
endif 
LOCAL_MODULE := ORB_SLAM2_EXCUTOR
LOCAL_C_INCLUDES+=$(LOCAL_PATH)/Thirdparty/eigen3
LOCAL_C_INCLUDES+=orb_slam2_android_nativefunc_OrbNdkHelper.h
LOCAL_SRC_FILES+=orb_slam2_android_nativefunc_OrbNdkHelper.cpp
LOCAL_SHARED_LIBRARIES+=ORB_SLAM2
LOCAL_SHARED_LIBRARIES+=g2o
LOCAL_LDLIBS += -llog -landroid -lEGL -lGLESv1_CM  

LOCAL_CPPFLAGS := -std=c++11 -pthread -frtti -fexceptions -ftemplate-backtrace-limit=0
LOCAL_CPPFLAGS += -D__cplusplus=201103L
LOCAL_PATH:=$(MAIN_DIR)
include $(BUILD_SHARED_LIBRARY)
############################################################
