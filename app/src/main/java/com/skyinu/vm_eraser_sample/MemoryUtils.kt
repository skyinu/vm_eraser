package com.skyinu.vm_eraser_sample

import android.os.Process
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

object MemoryUtils {
    //单位kB
    fun getVMSize(): Long {
        val mmapFile = File(getStatusFilePath())
        val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(mmapFile)))
        bufferedReader.lineSequence().iterator().forEach {
            if (it.startsWith("VmSize")) {
                return it.split(Regex("\\s+"))[1].toLong()
            }
        }
        return 0
    }

    private fun getStatusFilePath(): String {
        return "proc" + File.separator + Process.myPid() + File.separator + "status"
    }
}