# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.6

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Produce verbose output by default.
VERBOSE = 1

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake.exe

# The command to remove a file.
RM = /usr/bin/cmake.exe -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /cygdrive/d/Android/project/OrbSlam2Test/app

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /cygdrive/d/Android/project/OrbSlam2Test/app

# Include any dependencies generated for this target.
include CMakeFiles/SLAM2.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/SLAM2.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/SLAM2.dir/flags.make

CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o: CMakeFiles/SLAM2.dir/flags.make
CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o: src/main/cpp/native-lib.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/cygdrive/d/Android/project/OrbSlam2Test/app/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o"
	/usr/bin/c++.exe   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o -c /cygdrive/d/Android/project/OrbSlam2Test/app/src/main/cpp/native-lib.cpp

CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.i"
	/usr/bin/c++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /cygdrive/d/Android/project/OrbSlam2Test/app/src/main/cpp/native-lib.cpp > CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.i

CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.s"
	/usr/bin/c++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /cygdrive/d/Android/project/OrbSlam2Test/app/src/main/cpp/native-lib.cpp -o CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.s

CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.requires:

.PHONY : CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.requires

CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.provides: CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.requires
	$(MAKE) -f CMakeFiles/SLAM2.dir/build.make CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.provides.build
.PHONY : CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.provides

CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.provides.build: CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o


# Object files for target SLAM2
SLAM2_OBJECTS = \
"CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o"

# External object files for target SLAM2
SLAM2_EXTERNAL_OBJECTS =

cygSLAM2.dll: CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o
cygSLAM2.dll: CMakeFiles/SLAM2.dir/build.make
cygSLAM2.dll: libopencv_java3-NOTFOUND
cygSLAM2.dll: /usr/lib/w32api/liblog.a
cygSLAM2.dll: libORB_SLAM2.a
cygSLAM2.dll: libg2o.a
cygSLAM2.dll: CMakeFiles/SLAM2.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/cygdrive/d/Android/project/OrbSlam2Test/app/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX shared library cygSLAM2.dll"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/SLAM2.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/SLAM2.dir/build: cygSLAM2.dll

.PHONY : CMakeFiles/SLAM2.dir/build

CMakeFiles/SLAM2.dir/requires: CMakeFiles/SLAM2.dir/src/main/cpp/native-lib.cpp.o.requires

.PHONY : CMakeFiles/SLAM2.dir/requires

CMakeFiles/SLAM2.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/SLAM2.dir/cmake_clean.cmake
.PHONY : CMakeFiles/SLAM2.dir/clean

CMakeFiles/SLAM2.dir/depend:
	cd /cygdrive/d/Android/project/OrbSlam2Test/app && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /cygdrive/d/Android/project/OrbSlam2Test/app /cygdrive/d/Android/project/OrbSlam2Test/app /cygdrive/d/Android/project/OrbSlam2Test/app /cygdrive/d/Android/project/OrbSlam2Test/app /cygdrive/d/Android/project/OrbSlam2Test/app/CMakeFiles/SLAM2.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/SLAM2.dir/depend

