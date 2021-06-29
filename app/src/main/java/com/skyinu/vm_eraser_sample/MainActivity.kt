package com.skyinu.vm_eraser_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skyinu.vm_eraser.VMEraser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VMEraser.init()
        VMEraser.eraser("name")
    }
}