package com.skyinu.vm_eraser_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.skyinu.vm_eraser.MemoryFileParser
import com.skyinu.vm_eraser.VMEraser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            buildView()
        }
        VMEraser.init()
        VMEraser.eraser("/system/fonts/(?!Roboto).*\\.ttf")
    }

    @Composable
    private fun buildView() {
        Button(onClick = {
            MemoryFileParser.parse().forEach {
                if (it.name.contains("ttf")) {
                    Log.e("VMEraser", it.toString())
                }
            }
        }) {
            Text("print ttf memory info")
        }
    }
}