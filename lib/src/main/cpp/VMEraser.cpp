//
// Created by chen on 2021/6/28.
//

#include "com_skyinu_vm_eraser_VMEraser.h"
#include "android/log.h"
#include "sys/mman.h"

#ifdef __cplusplus
const char *VM_LOG = "vm_eraser";
extern "C" {
#endif
/**
 * https://man7.org/linux/man-pages/man3/munmap.3p.html
 */
JNIEXPORT jint JNICALL Java_com_skyinu_vm_1eraser_VMEraser_eraser
        (JNIEnv *env, jclass vmclass, jlong start, jlong size) {
    __android_log_print(ANDROID_LOG_INFO, VM_LOG, "native log %ld", start);
    return munmap((void *) start, size);
}

#ifdef __cplusplus
}
#endif