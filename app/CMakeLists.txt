# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.6.2)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
set(CMAKE_VERBOSE_MAKEFILE on)
set(libs "${CMAKE_SOURCE_DIR}/src/main/jniLibs")

#include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/Thirdparty/OpenCV-android-sdk/sdk/native/jni/include)
include_directories("C:/Users/Pascal/AndroidstudioProjects/OpenCV-3.4.1-android-sdk/sdk/native/jni/include")

add_library(libopencv_java4 SHARED IMPORTED )
set_target_properties(libopencv_java4 PROPERTIES IMPORTED_LOCATION "${libs}/${ANDROID_ABI}/libopencv_java3.so")
#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11 -fexceptions -frtti")

set(cpplibs "src/main/jni")
add_library(DLib
            SHARED
        ${cpplibs}/Thirdparty/DBoW2/DLib/include/DUtils/Random.h
        ${cpplibs}/Thirdparty/DBoW2/DLib/include/DUtils/Timestamp.h
        ${cpplibs}/Thirdparty/DBoW2/DLib/src/DUtils/Random.cpp
        ${cpplibs}/Thirdparty/DBoW2/DLib/src/DUtils/Timestamp.cpp
)
add_library(DBoW2
            SHARED
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/BowVector.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/FClass.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/FeatureVector.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/FORB.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/ScoringObject.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/TemplatedVocabulary.h
        ${cpplibs}/Thirdparty/DBoW2/src/BowVector.cpp
#        ${cpplibs}/Thirdparty/DBoW2/src/FClass.cpp
        ${cpplibs}/Thirdparty/DBoW2/src/FeatureVector.cpp
        ${cpplibs}/Thirdparty/DBoW2/src/FORB.cpp
        ${cpplibs}/Thirdparty/DBoW2/src/ScoringObject.cpp
#        ${cpplibs}/Thirdparty/DBoW2/src/TemplatedVocabulary.cpp
)
ADD_LIBRARY(g2o
            SHARED
        ${cpplibs}/Thirdparty/g2o/g2o/types/types_sba.h
        ${cpplibs}/Thirdparty/g2o/g2o/types/types_six_dof_expmap.h
        ${cpplibs}/Thirdparty/g2o/g2o/types/types_sba.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/types/types_six_dof_expmap.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/types/types_seven_dof_expmap.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/types/types_seven_dof_expmap.h
        ${cpplibs}/Thirdparty/g2o/g2o/types/se3quat.h
        ${cpplibs}/Thirdparty/g2o/g2o/types/se3_ops.h
        ${cpplibs}/Thirdparty/g2o/g2o/types/se3_ops.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_edge.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_binary_edge.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/hyper_graph_action.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_binary_edge.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/hyper_graph_action.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_multi_edge.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/hyper_graph.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_multi_edge.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/hyper_graph.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_unary_edge.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/linear_solver.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_unary_edge.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/marginal_covariance_cholesky.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_vertex.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/marginal_covariance_cholesky.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/base_vertex.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/matrix_structure.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/batch_stats.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/matrix_structure.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/batch_stats.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/openmp_mutex.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/block_solver.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/block_solver.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/parameter.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/parameter.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/cache.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/cache.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimizable_graph.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimizable_graph.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/solver.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/solver.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/creators.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_factory.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/estimate_propagator.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_factory.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/estimate_propagator.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/factory.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_property.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/factory.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/sparse_block_matrix.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/sparse_optimizer.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/sparse_block_matrix.hpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/sparse_optimizer.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/hyper_dijkstra.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/hyper_dijkstra.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/parameter_container.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/parameter_container.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_with_hessian.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_with_hessian.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_levenberg.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/optimization_algorithm_levenberg.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/jacobian_workspace.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/jacobian_workspace.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/robust_kernel.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/robust_kernel.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/robust_kernel_factory.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/robust_kernel_factory.h
        ${cpplibs}/Thirdparty/g2o/g2o/core/robust_kernel_impl.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/core/robust_kernel_impl.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/string_tools.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/color_macros.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/macros.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/timeutil.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/misc.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/timeutil.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/os_specific.c
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/os_specific.h
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/string_tools.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/property.cpp
        ${cpplibs}/Thirdparty/g2o/g2o/stuff/property.h
        )

include_directories(
        ${cpplibs}
        ${cpplibs}/Thirdparty
        ${cpplibs}/Thirdparty/g2o/g2o
        ${cpplibs}/ORB_SLAM2/include
        ${cpplibs}/Thirdparty/DBoW2/DLib/include/DUtils
        ${cpplibs}/Thirdparty/DBoW2/DLib/include
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2
        ${cpplibs}/Thirdparty/DBoW2/include

        ${cpplibs}/Thirdparty/eigen3
//        ${cpplibs}/Thirdparty/OpenCV-android-sdk/sdk/native/jni/include
)
add_library(ORB_SLAM2
            SHARED
        ${cpplibs}/ORB_SLAM2/include/System.h
        ${cpplibs}/ORB_SLAM2/include/Tracking.h
        ${cpplibs}/ORB_SLAM2/include/LocalMapping.h
        ${cpplibs}/ORB_SLAM2/include/LoopClosing.h
        ${cpplibs}/ORB_SLAM2/include/ORBextractor.h
        ${cpplibs}/ORB_SLAM2/include/ORBmatcher.h
        ${cpplibs}/ORB_SLAM2/include/FrameDrawer.h
        ${cpplibs}/ORB_SLAM2/include/Converter.h
        ${cpplibs}/ORB_SLAM2/include/MapPoint.h
        ${cpplibs}/ORB_SLAM2/include/KeyFrame.h
        ${cpplibs}/ORB_SLAM2/include/Map.h
        ${cpplibs}/ORB_SLAM2/include/Optimizer.h
        ${cpplibs}/ORB_SLAM2/include/PnPsolver.h
        ${cpplibs}/ORB_SLAM2/include/Frame.h
        ${cpplibs}/ORB_SLAM2/include/KeyFrameDatabase.h
        ${cpplibs}/ORB_SLAM2/include/Sim3Solver.h
        ${cpplibs}/ORB_SLAM2/include/Initializer.h
        #${cpplibs}/ORB_SLAM2/include/Common.h
        ${cpplibs}/ORB_SLAM2/src/System.cc
        ${cpplibs}/ORB_SLAM2/src/Tracking.cc
        ${cpplibs}/ORB_SLAM2/src/LocalMapping.cc
        ${cpplibs}/ORB_SLAM2/src/LoopClosing.cc
        ${cpplibs}/ORB_SLAM2/src/ORBextractor.cc
        ${cpplibs}/ORB_SLAM2/src/ORBmatcher.cc
        ${cpplibs}/ORB_SLAM2/src/FrameDrawer.cc
        ${cpplibs}/ORB_SLAM2/src/Converter.cc
        ${cpplibs}/ORB_SLAM2/src/MapPoint.cc
        ${cpplibs}/ORB_SLAM2/src/KeyFrame.cc
        ${cpplibs}/ORB_SLAM2/src/Map.cc
        ${cpplibs}/ORB_SLAM2/src/Optimizer.cc
        ${cpplibs}/ORB_SLAM2/src/PnPsolver.cc
        ${cpplibs}/ORB_SLAM2/src/Frame.cc
        ${cpplibs}/ORB_SLAM2/src/KeyFrameDatabase.cc
        ${cpplibs}/ORB_SLAM2/src/Sim3Solver.cc
        ${cpplibs}/ORB_SLAM2/src/Initializer.cc

        ############DBow2############
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/BowVector.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/FORB.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/FClass.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/FeatureVector.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/ScoringObject.h
        ${cpplibs}/Thirdparty/DBoW2/include/DBoW2/TemplatedVocabulary.h
        ${cpplibs}/Thirdparty/DBoW2/DLib/include/DUtils/Random.h
        ${cpplibs}/Thirdparty/DBoW2/DLib/include/DUtils/Timestamp.h
        ${cpplibs}/Thirdparty/DBoW2/src/BowVector.cpp
        ${cpplibs}/Thirdparty/DBoW2/src/FORB.cpp
        ${cpplibs}/Thirdparty/DBoW2/src/FeatureVector.cpp
        ${cpplibs}/Thirdparty/DBoW2/src/ScoringObject.cpp
        ${cpplibs}/Thirdparty/DBoW2/DLib/src/DUtils/Random.cpp
        ${cpplibs}/Thirdparty/DBoW2/DLib/src/DUtils/Timestamp.cpp
        ############OPENCV############

        )
add_library( # Sets the name of the library.
           native-lib
             # Sets the library as a shared library.
           SHARED
             # Provides a relative path to your source file(s).
            src/main/cpp/native-lib.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library(# Sets the name of the path variable.
             log-lib
             #D:/Android/Project/OrbSlam2Test/app/src/main/cpp/Thirdparty/OpenCV-android-sdk/sdk/native/libs/${ANDROID_ABI}
             # Specifies the name of the NDK library that
             # you want CMake to locate.
             log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries(native-lib
                      libopencv_java3 libopencv_java
                      DLib
                      DBoW2
                      g2o
                      ORB_SLAM2
                       ${log-lib}
                      # Links the target library to the log library
                      # included in the NDK.
                      )