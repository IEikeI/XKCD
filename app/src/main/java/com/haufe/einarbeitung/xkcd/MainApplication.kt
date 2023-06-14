package com.haufe.einarbeitung.xkcd

import android.app.Application
import android.content.Context

/**
 * MainApplication class for global applicationContext
 */
open class MainApplication : Application() {
    companion object {
        lateinit var instance: MainApplication
            private set

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}