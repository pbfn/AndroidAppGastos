package com.pedrobruno.appgastos

import android.app.Application
import com.pedrobruno.appgastos.di.firebaseModule
import com.pedrobruno.appgastos.di.repositoryModule
import com.pedrobruno.appgastos.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(
                repositoryModule,
                firebaseModule,
                viewModelModule
            )
        }
    }
}