package fr.kodesparkle.sephorademo.app

import android.app.Application
import android.content.pm.ApplicationInfo
import fr.kodesparkle.sephorademo.app.di.appModule
import fr.kodesparkle.sephorademo.app.di.kotlinCoroutinesProvidersModule
import fr.kodesparkle.sephorademo.data.di.dataModule
import fr.kodesparkle.sephorademo.domain.di.domainModule
import fr.kodesparkle.sephorademo.presentation.di.viewModelModule
import fr.kodesparkle.sephorademo.utils.TimberConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class SephoraApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        // Start Koin
        startKoin {
            // declare used Android context
            androidContext(this@SephoraApplication)
            // declare modules
            modules(
                kotlinCoroutinesProvidersModule,
                appModule,
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }

    private fun setupTimber() {
        val isDebuggable = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        TimberConfiguration.configure(isDebuggable)
    }
}