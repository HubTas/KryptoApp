package pl.doz.kryptoapp.viewmodel

import pl.doz.kryptoapp.model.CoinValueDTO

sealed class CoinValueState {
    object Start : CoinValueState()
    object Loading : CoinValueState()
    data class Success(val coin: List<CoinValueDTO>) : CoinValueState()
    data class Failure(val error: String) : CoinValueState()
}