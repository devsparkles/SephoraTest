package fr.kodesparkle.sephorademo.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import fr.kodesparkle.sephorademo.presentation.products.ProductViewModel

val viewModelModule = module {

    viewModelOf(::ProductViewModel)

}