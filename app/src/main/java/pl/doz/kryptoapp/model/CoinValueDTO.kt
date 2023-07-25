package pl.doz.kryptoapp.model

data class CoinValueDTO(
    val close: Double,
    val high: Double,
    val low: Double,
    val market_cap: Long,
    val open: Double,
    val time_close: String,
    val time_open: String,
    val volume: Long
)
fun CoinValueDTO.toCoinValueModel(): CoinValueModel {
    return CoinValueModel(
        high = high,
        volume = volume
    )
}
