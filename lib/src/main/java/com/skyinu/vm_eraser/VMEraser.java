package com.skyinu.vm_eraser;

import android.util.Log;

import java.util.List;
import java.util.regex.Pattern;

public class VMEraser {
    private static final String TAG = "VMEraser";

    public static void init() {
        System.loadLibrary("vm_eraser");
    }

    public static boolean eraser(String regex) {
        List<MapItemModel> mapItemModelList = MemoryFileParser.INSTANCE.parse();
        Pattern pattern = Pattern.compile(regex);
        boolean result = false;
        for (MapItemModel item : mapItemModelList) {
            if (!item.getName().isEmpty() && pattern.matcher(item.getName()).find()) {
                Log.i(TAG, "erase " + item.toString());
                int res = eraser(item.getStartAddress(), item.getEndAddress() - item.getStartAddress());
                result = result || (res == 0);
                Log.i(TAG, "result = " + res);
            }
        }
        return result;
    }

    public static native int eraser(long startAddress, long size);
}
