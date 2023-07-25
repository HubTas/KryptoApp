package pl.doz.kryptoapp.viewmodel

import pl.doz.kryptoapp.model.CoinDTO
import pl.doz.kryptoapp.model.CoinValueDTO
import pl.doz.kryptoapp.model.CryptoDTO

interface CryptoRepository {
    suspend fun getCrypto(): Result<List<CryptoDTO>>

    suspend fun getCoin(coinId: String): Result<CoinDTO>

    suspend fun getCoinValue(coinId: String): Result<List<CoinValueDTO>>
}
