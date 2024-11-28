package fr.kodesparkle.sephorademo.app.di
import fr.kodesparkle.sephorademo.app.di.coroutines.CoroutineDispatcherProvider
import fr.kodesparkle.sephorademo.app.di.coroutines.DispatcherProvider
import org.koin.dsl.module


val kotlinCoroutinesProvidersModule = module {
    factory<DispatcherProvider> { CoroutineDispatcherProvider() }
}

val appModule = module {

    // should define app wide dependencies here
}