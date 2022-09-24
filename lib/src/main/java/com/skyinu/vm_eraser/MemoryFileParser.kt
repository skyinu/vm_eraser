package com.skyinu.vm_eraser

import android.os.Process
import java.io.*

object MemoryFileParser {
    private const val PROC_FILE_NAME = "proc"
    private const val MEMORY_FILE_NAME = "maps"

    /**
     * @hide
     */
    fun parse(): List<MapItemModel> {
        val mapItemModels = mutableListOf<MapItemModel>()
        val mmapFile = File(getMMapFilePath())
        val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(mmapFile)))
        bufferedReader.forEachLine {
            mapItemModels.add(parseStringItemToMapItemModel(it))
        }
        return mapItemModels
    }

    private fun parseStringItemToMapItemModel(stringItem: String): MapItemModel {
        val splits = stringItem.split(" ")
        val addressRange = splits[0].split("-")
        val name = splits[splits.size - 1]
        return MapItemModel(name, addressRange[0].toLong(16), addressRange[1].toLong(16))
    }

    private fun getMMapFilePath(): String {
        return PROC_FILE_NAME + File.separator + Process.myPid() + File.separator + MEMORY_FILE_NAME
    }
}