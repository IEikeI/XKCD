package com.haufe.einarbeitung.xkcd

import android.app.Application
import android.content.Context

/**
 * MainApplication class for global applicationContext
 */
open class MainApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}