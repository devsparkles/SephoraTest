package fr.kodesparkle.sephorademo.app.di.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class CoroutineDispatcherProvider : DispatcherProvider {
    override val main: CoroutineContext get() = Dispatchers.Main
    override val default: CoroutineContext get() = Dispatchers.Default
    override val io: CoroutineContext get() = Dispatchers.IO
    override val unconfined: CoroutineContext get() = Dispatchers.Unconfined
    @OptIn(ExperimentalCoroutinesApi::class)
    override val limitedParallelism: CoroutineContext get() = Dispatchers.Default.limitedParallelism(1)
}
