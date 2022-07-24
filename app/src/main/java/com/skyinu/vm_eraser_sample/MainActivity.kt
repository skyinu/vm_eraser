package com.skyinu.vm_eraser_sample

import android.Manifest
import android.app.AppOpsManager
import android.app.AsyncNotedAppOp
import android.app.SyncNotedAppOp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import com.skyinu.vm_eraser.VMEraser
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private var vmSizeBefore = 0L
    private var nativeHeapSize = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            buildView()
        }
        VMEraser.init()
        val appOps = getSystemService(AppOpsManager::class.java)
        appOps.setOnOpNotedCallback(
            Executors.newSingleThreadExecutor(),
            object : AppOpsManager.OnOpNotedCallback() {
                override fun onNoted(op: SyncNotedAppOp) {
                    Log.e("TAG1", "onNoted$op")
                }

                override fun onSelfNoted(op: SyncNotedAppOp) {
                    Log.e(
                        "TAG1", "onSelfNoted$op" + Log.getStackTraceString(
                            Throwable()
                        )
                    )
                }

                override fun onAsyncNoted(asyncOp: AsyncNotedAppOp) {
                    Log.e("TAG1", "onAsyncNoted$asyncOp")
                }
            })
        appOps.startWatchingMode(AppOpsManager.OPSTR_CAMERA,
            applicationContext.packageName, object : AppOpsManager.OnOpNotedCallback(),
                AppOpsManager.OnOpChangedListener {
                override fun onNoted(op: SyncNotedAppOp) {
                    Log.e("TAG1", "onNoted onNoted $op")
                }

                override fun onSelfNoted(op: SyncNotedAppOp) {
                    Log.e(
                        "TAG1", "onSelfNoted onNoted $op" + Log.getStackTraceString(
                            Throwable()
                        )
                    )
                }

                override fun onAsyncNoted(asyncOp: AsyncNotedAppOp) {
                    Log.e("TAG1", "onAsyncNoted onNoted $asyncOp")
                }

                override fun onOpChanged(op: String?, packageName: String?) {
                    Log.e(
                        "TAG1", "onOpChanged onNoted$op $packageName" + Log.getStackTraceString(
                            Throwable()
                        )
                    )
                }
            })
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
            Button(onClick = {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    100
                )
            }) {
                Text(text = "request permission camera")
            }
            Button(onClick = {
                val appOps = getSystemService(AppOpsManager::class.java)
                appOps.startOp(
                    AppOpsManager.OPSTR_CALL_PHONE,
                    applicationContext.applicationInfo.uid,
                    applicationContext.packageName
                )
            }) {
                Text(text = "test start op")
            }
            Button(onClick = {
                val appOps = getSystemService(AppOpsManager::class.java)
                appOps.noteOp(
                    AppOpsManager.OPSTR_FINE_LOCATION,
                    applicationContext.applicationInfo.uid,
                    applicationContext.packageName
                )
            }) {
                Text(text = "test note op")
            }
        }
    }
}