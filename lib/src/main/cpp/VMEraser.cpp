//
// Created by chen on 2021/6/28.
//

#include "com_skyinu_vm_eraser_VMEraser.h"
#include "android/log.h"

#ifdef __cplusplus
const char *VM_LOG = "vm_eraser";
extern "C" {
#endif
JNIEXPORT jboolean JNICALL Java_com_skyinu_vm_1eraser_VMEraser_eraser
        (JNIEnv *env, jclass vmclass, jstring address) {
    __android_log_print(ANDROID_LOG_INFO, VM_LOG, "native log %s", address);
    return false;
}

#ifdef __cplusplus
}
#endif