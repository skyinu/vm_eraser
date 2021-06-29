package com.skyinu.vm_eraser;

public class VMEraser {
    public static void init(){
        System.loadLibrary("vm_eraser");
    }
    public static native boolean eraser(String addressName);
}
