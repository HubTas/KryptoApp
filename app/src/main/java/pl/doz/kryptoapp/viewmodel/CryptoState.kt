package pl.doz.kryptoapp.viewmodel

import pl.doz.kryptoapp.model.CryptoDTO

sealed class CryptoState {
    object Start : CryptoState()
    object Loading : CryptoState()
    data class Success(val cryptoList: List<CryptoDTO>) : CryptoState()
    data class Failure(val error: String) : CryptoState()
}
