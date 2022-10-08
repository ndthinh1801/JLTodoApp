package com.example.jltodoapp.di

import android.content.Context
import androidx.room.Room
import com.example.jltodoapp.common.Constants
import com.example.jltodoapp.data.local.AppDataBase
import com.example.jltodoapp.data.local.LocalService
import com.example.jltodoapp.data.remote.ApiService
import com.example.jltodoapp.data.remote.NetworkService
import com.example.jltodoapp.data.repository.CallRepository
import com.example.jltodoapp.data.repository.ListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "JLTodo.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkService(apiService: ApiService): NetworkService {
        return NetworkService(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalService(dataBase: AppDataBase): LocalService {
        return LocalService(dataBase.itemToSellDao())
    }

    @Provides
    @Singleton
    fun provideCallRepository(networkService: NetworkService): CallRepository {
        return CallRepository(networkService)
    }

    @Singleton
    @Provides
    fun provideListingRepository(
        networkService: NetworkService,
        localService: LocalService
    ): ListingRepository {
        return ListingRepository(networkService, localService)
    }
}