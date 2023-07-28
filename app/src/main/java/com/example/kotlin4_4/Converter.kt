package com.example.kotlin4_4

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream


class Converter {
    @SuppressLint("SdCardPath")
    fun convertJpgToPng(): String {
        try {
            val jpgFile = BitmapFactory.decodeFile("/sdcard/holiday.jpg") // откуда загрузить jpg
            val out = FileOutputStream(File("/sdcard/holiday.png")) // куда сохранить png, с каким именем
            jpgFile.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            return "Successful convert"
        } catch (e: Exception){
            return e.toString()
        }
    }
}