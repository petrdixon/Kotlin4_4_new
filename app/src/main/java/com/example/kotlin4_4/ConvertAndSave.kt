package com.example.kotlin4_4

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

class ConvertAndSave {
    fun convertAndSave(jpgFile: Bitmap): String {
        try {
            val out = FileOutputStream(File("/sdcard/holiday.png")) // куда сохранить png, с каким именем
            jpgFile.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            return "Successful convert"
        } catch (e: Exception) {
            return e.toString()
        }
    }
}