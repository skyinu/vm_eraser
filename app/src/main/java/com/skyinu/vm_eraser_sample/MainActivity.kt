package com.skyinu.vm_eraser_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.skyinu.vm_eraser.MemoryFileParser
import com.skyinu.vm_eraser.VMEraser

class MainActivity : AppCompatActivity() {
    private var vmSizeBefore = 0L
    private var nativeHeapSize = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            buildView()
        }
        VMEraser.init()
    }

    @Composable
    private fun buildView() {
        Column {
            Button(onClick = {
                vmSizeBefore = MemoryUtils.getVMSize()
                nativeHeapSize = Debug.getNativeHeapSize()
                VMEraser.eraser("/system/fonts/(?!Roboto).*\\.ttf")
                setContent { buildView() }
            }) {
                Text("click to free vm")
            }
            Text(
                "vm change: before = $vmSizeBefore kB, " +
                        "now ${MemoryUtils.getVMSize()} kB, " +
                        "gap = ${vmSizeBefore - MemoryUtils.getVMSize()}kB"
            )
            Text(
                "native heap change: before = $nativeHeapSize B, " +
                        "now ${Debug.getNativeHeapSize()} B, " +
                        "gap = ${nativeHeapSize - Debug.getNativeHeapSize()} B"
            )
        }
    }
}