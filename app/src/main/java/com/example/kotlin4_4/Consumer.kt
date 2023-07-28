package com.example.kotlin4_4

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class Consumer (val producer: GetFile) {
    fun exec() {
        Observable.create<Bitmap> {
                emitter -> producer.just() }
            .subscribeOn(Schedulers.io())  // Observable создается в IO-потоке
            .observeOn(Schedulers.computation())
            .map { emitter ->
                println("*********** Thread.currentThread().name ${Thread.currentThread().name}") // некое действие с данными выполнится в computation-потоке (поток указали в observeOn(Schedulers.computation())) TODO добавить здесь конвертацию?
                MainPresenter(Converter()).startConvertRxJava(emitter)
            }
            .observeOn(AndroidSchedulers.mainThread()) // подписчики получат данные в основном потоке
            .subscribe({ s ->
                println("********** onNext: $s")


//            usersListPresenter.users.addAll(s) // эта и строка ниже передают список для публикации.
//            viewState.updateList()
            }, { e ->
                println("********** onError: ${e.message}")
            }, {
                println("*********** onComplete")
            })

    }


}