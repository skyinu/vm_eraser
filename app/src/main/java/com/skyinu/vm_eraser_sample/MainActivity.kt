package com.skyinu.vm_eraser_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.skyinu.vm_eraser.MemoryFileParser
import com.skyinu.vm_eraser.VMEraser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VMEraser.init()
        VMEraser.eraser("/system/fonts/(?!Roboto).*\\.ttf")
        findViewById<View>(R.id.hello).setOnClickListener {
            MemoryFileParser.parse().forEach {
                if (it.name.contains("ttf")) {
                    Log.e("VMEraser", it.toString())
                }
            }
        }
    }
}