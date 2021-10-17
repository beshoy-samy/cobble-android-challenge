//
// Created by Beshoy Samy on 9/28/2021.
//
#include <jni.h>
#include <string>

extern "C" jstring
Java_com_cobble_network_NetworkKeys_getBaseUrl(JNIEnv *env, jobject object) {
    std::string baseUrl = "https://api.openweathermap.org/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" jstring
Java_com_cobble_network_NetworkKeys_getAPIKey(JNIEnv *env, jobject object) {
    std::string apiKey = "6cd26ab58acfd256cd629192bd968cca";
    return env->NewStringUTF(apiKey.c_str());
}

extern "C" jstring
Java_com_cobble_network_NetworkKeys_getImageBaseUrl(JNIEnv *env, jobject object) {
    std::string imageUrl = "https://openweathermap.org/img/wn/";
    return env->NewStringUTF(imageUrl.c_str());
}