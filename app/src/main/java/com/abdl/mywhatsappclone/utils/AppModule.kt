package com.abdl.mywhatsappclone.utils

import android.app.Application
import androidx.room.Room
import com.abdl.mywhatsappclone.source.local.WACDatabase
import com.abdl.mywhatsappclone.source.network.ApiConfig
import com.abdl.mywhatsappclone.source.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): WACDatabase =
        Room.databaseBuilder(app, WACDatabase::class.java, "wac_database")
            .build()

    @Provides
    fun provideLmkDao(db: WACDatabase) = db.getWACDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope