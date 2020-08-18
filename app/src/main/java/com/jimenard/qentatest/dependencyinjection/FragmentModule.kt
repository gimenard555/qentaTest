package com.jimenard.qentatest.dependencyinjection

import com.jimenard.qentatest.ui.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Listado de las actividades que necesitan de inyecccion de dependencias para el view model
 * del modulo de compradores
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}