package pl.doz.kryptoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.doz.kryptoapp.databinding.CoinViewBinding
import pl.doz.kryptoapp.viewmodel.CoinState
import pl.doz.kryptoapp.viewmodel.CoinValueState
import pl.doz.kryptoapp.viewmodel.CoinViewModel
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class CoinViewFragment : Fragment() {
    private lateinit var _binding: CoinViewBinding
    private val binding get() = _binding
    private val coinViewModel: CoinViewModel by viewModels()
    private val args: CoinViewFragmentArgs by navArgs()
    private lateinit var calendarView: CalendarView
    private val defaultTimestamp: Long = System.currentTimeMillis()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CoinViewBinding.inflate(layoutInflater, container, false)
        val coinId = args.selectedCrypto

        with(binding) {
            coinViewModel.getCoin(coinId)
            coinViewModel.getCoinValue(coinId)
        }

        lifecycleScope.launch {
            coinViewModel.coinState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is CoinState.Start -> {}
                    is CoinState.Loading -> {}
                    is CoinState.Success -> {
                        val coin = state.coin
                        binding.coinName.text = coin.name
                        binding.coinDescription.text = coin.description
                        binding.coinIdText.text = coin.id
                        binding.coinType.text = coin.type
                        binding.coinSymbol.text = coin.symbol
                        binding.coinRank.text = coin.rank.toString()
                        Glide.with(requireContext())
                            .load(coin.logo)
                            .into(binding.coinImage)
                        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                        val startedAtDate = sdf.parse(coin.started_at)
                        val startedAtTimestamp = startedAtDate?.time ?: defaultTimestamp
                        binding.coinDate.date = startedAtTimestamp
                        binding.coinDate.setOnDateChangeListener{ _, year, month, dayOfMonth ->
                            binding.coinDate.date = startedAtTimestamp
                        }
                    }
                    is CoinState.Failure -> {}
                }
            }
            coinViewModel.coinValueState.observe(viewLifecycleOwner) { valueState ->
                when (valueState) {
                    is CoinValueState.Start -> {}
                    is CoinValueState.Loading -> {}
                    is CoinValueState.Success -> {
                        val coinValue = valueState.coin[0]
                        binding.coinHP.text = coinValue.high.toString()
                        binding.coinVolume.text = coinValue.volume.toString()
                    }
                    is CoinValueState.Failure -> {}
                }
            }
        }

        return binding.root
    }
}
