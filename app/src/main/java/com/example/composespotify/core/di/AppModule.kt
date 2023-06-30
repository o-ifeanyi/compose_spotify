package com.example.composespotify.core.di

import android.content.Context
import com.example.composespotify.core.service.DataStoreService
import com.example.composespotify.core.service.ApiService
import com.example.composespotify.core.util.Constants
import com.example.composespotify.features.data.repository.AuthRepositoryImpl
import com.example.composespotify.features.data.repository.DetailRepositoryImpl
import com.example.composespotify.features.data.repository.HomeRepositoryImpl
import com.example.composespotify.features.data.repository.SearchRepositoryImpl
import com.example.composespotify.features.domain.repository.AuthRepository
import com.example.composespotify.features.domain.repository.DetailRepository
import com.example.composespotify.features.domain.repository.HomeRepository
import com.example.composespotify.features.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreService {
        return DataStoreService(context = context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor(provideDataStore(context)))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): ApiService {
        return Retrofit.Builder().client(provideOkHttpClient(context)).baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        dataStoreService: DataStoreService
    ): AuthRepository = AuthRepositoryImpl(apiService, dataStoreService)

    @Provides
    @Singleton
    fun providePlaylistRepository(
        apiService: ApiService
    ): HomeRepository = HomeRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideDetailRepository(
        apiService: ApiService
    ): DetailRepository = DetailRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideSearchRepository(
        apiService: ApiService
    ): SearchRepository = SearchRepositoryImpl(apiService)
}