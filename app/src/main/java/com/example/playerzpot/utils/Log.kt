package com.demo.myapplication.utils

import android.util.Log

class Log {
    companion object {
        fun LOG_D(tag: String?, message: String?) {
//            if (BuildConfig.DEBUG) {
            if (message != null) {
                Log.d(tag, message)
            }
//            }
        }

        fun LOG_E(tag: String?, message: String?) {
//            if (BuildConfig.DEBUG) {
            if (message != null) {
                Log.e(tag, message)
            }
//            }
        }

        fun LOG_V(tag: String?, message: String?) {
//            if (BuildConfig.DEBUG) {
            if (message != null) {
                Log.v(tag, message)
            }
//            }
        }

        fun LOG_I(tag: String?, message: String?) {
//            if (BuildConfig.DEBUG) {
            if (message != null) {
                Log.i(tag, message)
            }
//            }
        }


    }
}