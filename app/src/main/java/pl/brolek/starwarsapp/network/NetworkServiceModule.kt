package pl.brolek.starwarsapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
@Module
class NetworkServiceModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://swapi.co/api/"

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitClient(baseUrl: String, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()


}