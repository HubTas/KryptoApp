package pl.doz.kryptoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): ViewModel() {
    private val _cryptoState: MutableStateFlow<CryptoState> = MutableStateFlow(CryptoState.Start)
    val cryptoState: StateFlow<CryptoState> = _cryptoState

    init {
        getCrypto()
        Log.d("crypto", cryptoState.value.toString())
    }

    fun getCrypto() {
        viewModelScope.launch {
            _cryptoState.value = CryptoState.Loading
            cryptoRepository.getCrypto(
            ).onSuccess {
                _cryptoState.value = CryptoState.Success(it)
            }.onFailure {
                _cryptoState.value = CryptoState.Failure(it.message!!)
            }
        }
    }
}