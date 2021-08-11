# vm_eraser
## Description
just a bridge to call munmap method to help you free some virtual memory which is useless for your app

## Usage
+ import library to your project

```
dependencies {
	implementation 'com.github.skyinu:vm_eraser:0.0.1'
}
```

+ call `com.skyinu.vm_eraser.VMEraser.init` function to init library

+ call `com.skyinu.vm_eraser.VMEraser.eraser(java.lang.String)` to free memory, for example

```
VMEraser.eraser("/system/fonts/(?!Roboto).*\\.ttf")
```
