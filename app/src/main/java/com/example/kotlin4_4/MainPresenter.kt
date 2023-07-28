package com.example.kotlin4_4

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import io.reactivex.rxjava3.core.Observer
import java.io.File
import java.io.FileOutputStream

class MainPresenter(val converter: Converter) : MvpPresenter<MainView>() { // converter это Model
    override fun onFirstViewAttach() { // работает только при запуске приложения/первом присоединении View
//        getJpg()
        println("******************** in MainPresenter/onFirstViewAttach()")

        Consumer(GetFile()).exec() // стартую RXJava
    }

    override fun attachView(view: MainView?) { // работает при запуске приложения и поворотах экрана/при каждом присоединении View
        super.attachView(view)
    }

    fun startConvert() {
        val textStatus = converter.convertJpgToPng() // запускаю конвертацию файла
        viewState.displayStatus(textStatus) // показываю статус конвертации на экране
    }

    fun startConvertRxJava(bitmap: Bitmap) {
        val out = FileOutputStream(File("/sdcard/holiday.png")) // куда сохранить png, с каким именем
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
        out.close()
    }



}
