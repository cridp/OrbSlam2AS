package com.ntanougat.orbslam2test.NdkHelper;

public class OrbNdkHelper {

    public static native void initSystemWithParameters(String VOCPath,String calibrationPath);

    public static native int[] startCurrentORB(double curTimeStamp,int[] data,int w,int h);
    public native static int[] startCurrentORBForCamera(double curTimeStamp,long addr,int w,int h);
    public native static void glesInit();
    public native static void glesRender();
    public native static void glesResize(int width, int height);



}
