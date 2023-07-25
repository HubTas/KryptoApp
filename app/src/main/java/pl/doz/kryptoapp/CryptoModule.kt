package pl.doz.kryptoapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.doz.kryptoapp.viewmodel.CryptoRepository
import pl.doz.kryptoapp.viewmodel.CryptoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptoModule {
    @Provides
    @Singleton
    fun provideCryptoApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(apiService: ApiService): CryptoRepository {
        return CryptoRepositoryImpl(apiService)
    }
}