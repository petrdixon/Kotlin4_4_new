package com.example.kotlin4_4

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import io.reactivex.rxjava3.core.Observable

class GetFile {
    @SuppressLint("SdCardPath")
    fun getJpg(): Bitmap {
        val jpgFile = BitmapFactory.decodeFile("/sdcard/holiday.jpg") // откуда загрузить jpg
        return jpgFile
    }
    fun just(): Observable<Bitmap> {
        return Observable.just(getJpg()) // создаем Observable
    }
}
