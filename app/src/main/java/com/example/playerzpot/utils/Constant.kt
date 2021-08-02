package com.demo.myapplication.utils

import android.content.Context
import android.widget.Toast
import java.io.IOException
import java.nio.charset.Charset

class Constant {

    companion object {
        public fun loadJSONFromAsset(context: Context, filePath: String): String {
            val json: String?
            try {
                val assets = context.resources.assets
                val inputStream = assets.open(filePath)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                val charset: Charset = Charsets.UTF_8
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, charset)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return ""
            }
            return json
        }

        fun showShortToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        val DISCOVER_FRAGMENT: String  = "discoverFragment"
        val BUCKET_ID_PARAMETER = "bucketId"
        val COUNT_PARAMETER = "count"
    }

}