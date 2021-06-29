cmake_minimum_required(VERSION 3.6.0)
add_library(vm_eraser SHARED
        VMEraser.cpp)
target_link_libraries(vm_eraser log)
#target_include_directories(vm_eraser)