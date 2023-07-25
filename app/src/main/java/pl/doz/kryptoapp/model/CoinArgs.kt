package pl.doz.kryptoapp.model

import android.os.Bundle
import androidx.navigation.NavArgs

data class CoinArgs(val argCoin: String): NavArgs {
    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("argValue", argCoin)
        return bundle
    }

    companion object {
        fun fromBundle(bundle: Bundle): CoinArgs {
            return CoinArgs(bundle.getString("argValue", ""))
        }
    }
}