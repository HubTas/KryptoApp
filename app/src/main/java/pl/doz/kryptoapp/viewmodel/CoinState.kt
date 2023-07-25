package pl.doz.kryptoapp.viewmodel

import pl.doz.kryptoapp.model.CoinDTO

sealed class CoinState {
    object Start : CoinState()
    object Loading : CoinState()
    data class Success(val coin: CoinDTO) : CoinState()
    data class Failure(val error: String) : CoinState()
}