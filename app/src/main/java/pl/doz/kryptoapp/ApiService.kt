package pl.doz.kryptoapp

import pl.doz.kryptoapp.model.CoinDTO
import pl.doz.kryptoapp.model.CoinValueDTO
import pl.doz.kryptoapp.model.CryptoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/coins")
    suspend fun getCrypto(): List<CryptoDTO>

    @GET("v1/coins/{coinId}")
    suspend fun getCoin (
        @Path("coinId") coinId: String
    ): CoinDTO

    @GET("v1/coins/{coinId}/ohlcv/latest")
    suspend fun getCoinValue (
        @Path("coinId") coinId: String
    ): List<CoinValueDTO>
}
