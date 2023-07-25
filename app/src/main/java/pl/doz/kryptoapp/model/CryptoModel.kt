package pl.doz.kryptoapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CryptoModel(
    val name: String,
    val rank: Int,
    val id: String
)