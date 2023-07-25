package pl.doz.kryptoapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): ViewModel() {
    private val _coinState: MutableLiveData<CoinState> = MutableLiveData(CoinState.Start)
    val coinState: MutableLiveData<CoinState> = _coinState
    val getCoinSuccess = MutableLiveData(false)
    private val _coinValueState: MutableLiveData<CoinValueState> = MutableLiveData(CoinValueState.Start)
    val coinValueState: MutableLiveData<CoinValueState> = _coinValueState
    val getCoinValueSuccess = MutableLiveData(false)

    var mediatorLiveData = MediatorLiveData<Boolean>()

    init {
        combineLiveData()
    }

    fun combineLiveData() {
        mediatorLiveData.addSource(getCoinSuccess) {
            mediatorLiveData.value = it && getCoinValueSuccess.value ?: false
        }

        mediatorLiveData.addSource(getCoinValueSuccess) {
            mediatorLiveData.value = it && getCoinSuccess.value ?: false
        }
    }


    fun getCoin(coinId: String) {
        viewModelScope.launch {
            _coinState.value = CoinState.Loading
            cryptoRepository.getCoin(
                coinId = coinId
            ).onSuccess {
                _coinState.value = CoinState.Success(it)
            }.onFailure {
                _coinState.value = CoinState.Failure(it.message!!)
            }
        }
    }

    fun getCoinValue(coinId: String) {
        viewModelScope.launch {
            _coinValueState.value = CoinValueState.Loading
            cryptoRepository.getCoinValue(
                coinId = coinId
            ).onSuccess {
                _coinValueState.value = CoinValueState.Success(it)
            }.onFailure {
                _coinValueState.value = CoinValueState.Failure(it.message!!)
            }
        }
    }
}