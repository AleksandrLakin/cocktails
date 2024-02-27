package com.example.mymessage.app

import android.app.Application
import com.example.mymessage.di.AppComponent
import com.example.mymessage.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

}