package com.example.kotlin4_4

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.internal.util.HalfSerializer.onNext
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.File
import java.io.FileOutputStream
import java.lang.Thread.currentThread


class MainPresenter() : MvpPresenter<MainView>() {
    val converter = Converter() // converter это Model

    override fun onFirstViewAttach() { // работает только при запуске приложения/первом присоединении View
    }

    override fun attachView(view: MainView?) { // работает при запуске приложения и поворотах экрана/при каждом присоединении View
        super.attachView(view)
    }

    fun startConvert() {
        converter.justGetJpg()
            .doOnNext { bitmap -> println("******* Emitting item " + bitmap + " on: " + currentThread().name) }
            .subscribeOn(Schedulers.io()) // читаю файл в потоке IO
            .observeOn(Schedulers.computation()) // переключаюсь на поток computation, в нем конфертирую файл
            .map { bitmap -> converter.convertAndSave(bitmap) }
            .observeOn(AndroidSchedulers.mainThread()) // переключаюсь на поток main и выдаю результат
            .subscribe { textStatus -> viewState.displayStatus(textStatus) } // показываю статус конвертации на экране
    }
}
