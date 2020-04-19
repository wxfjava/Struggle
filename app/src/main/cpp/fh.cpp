#include <jni.h>
#include <stdio.h>
#include <android/log.h>

#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO, "FH-LOG", __VA_ARGS__)

extern "C"
JNIEXPORT jstring JNICALL
Java_com_wxfjava_struggle_MainActivity_hello(JNIEnv *env, jobject thiz, jstring name) {

    char *cname = (char *) env->GetStringUTFChars(name, 0);
    char result[50];
    sprintf(result, "Hello %s,I am JNI.", cname);
    LOGI("获得android参数：%s", cname);
    env->ReleaseStringUTFChars(name, cname);

    return env->NewStringUTF(result);
}
