package com.jimenard.qentatest.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


object Utils {

    fun getImageFromString(strBase64: String): Bitmap? {
        val decodedString: ByteArray = Base64.decode(strBase64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun getBase64EncodedImage(imageURL: String?): String {
        val bos = ByteArrayOutputStream()
        getBitmapFromURL(imageURL)!!.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        return Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT)
    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url
                .openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun internetIsConnected(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}