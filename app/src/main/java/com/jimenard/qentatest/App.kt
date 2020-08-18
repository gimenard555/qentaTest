package com.jimenard.qentatest

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.jimenard.qentatest.dependencyinjection.DaggerQuentaComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Clase que representa la aplicacion
 */
class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAnyAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        DaggerQuentaComponent.builder()
            .appContext(this)
            .create(this)
            .inject(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = this.dispatchingAnyAndroidInjector
}