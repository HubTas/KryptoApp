package pl.doz.kryptoapp.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.doz.kryptoapp.ApiService
import pl.doz.kryptoapp.model.CoinDTO
import pl.doz.kryptoapp.model.CoinValueDTO
import pl.doz.kryptoapp.model.CryptoDTO
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CryptoRepository {
    override suspend fun getCrypto(): Result<List<CryptoDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success (
                    apiService.getCrypto()
                )
            } catch (e: HttpException) {
                Result.failure(e)
            } catch (e: IOException) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getCoin(coinId: String): Result<CoinDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success (
                    apiService.getCoin(coinId)
                )
            } catch (e: HttpException) {
                Result.failure(e)
            } catch (e: IOException) {
                Result.failure(e)
            }
        }

    }

    override suspend fun getCoinValue(coinId: String): Result<List<CoinValueDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(
                    apiService.getCoinValue(coinId)
                )
            } catch (e: HttpException) {
                Result.failure(e)
            } catch (e: IOException) {
                Result.failure(e)
            }
        }
    }
}