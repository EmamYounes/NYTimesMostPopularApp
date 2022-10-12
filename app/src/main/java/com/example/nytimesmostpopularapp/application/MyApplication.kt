package com.example.nytimesmostpopularapp.application

import android.app.Application
import com.example.nytimesmostpopularapp.data.network.api.RetrofitBuilder
import com.example.nytimesmostpopularapp.data.network.interceptor.NetworkConnectionInterceptor
import com.example.nytimesmostpopularapp.data.model.MyRepository
import com.example.nytimesmostpopularapp.data.model.RemoteData
import com.example.swensonheweatherapp.view_model.MyViewModel
import com.example.swensonheweatherapp.view_model.MyViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {


    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { RetrofitBuilder(instance()) }
        bind() from singleton { RemoteData(instance()) }
        bind() from singleton { MyRepository(instance()) }
        bind() from singleton { MyViewModel(instance()) }
        bind() from singleton { MyViewModelFactory(instance()) }
    }
}