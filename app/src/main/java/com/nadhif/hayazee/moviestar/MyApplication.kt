package com.nadhif.hayazee.moviestar

import android.app.Application
import com.nadhif.hayazee.moviestar.module.RepositoryModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RepositoryModule.initModule(this)
    }
}