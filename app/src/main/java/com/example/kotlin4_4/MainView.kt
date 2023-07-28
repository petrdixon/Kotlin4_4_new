package com.example.kotlin4_4

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun displayStatus(convertStatus: String)
}

