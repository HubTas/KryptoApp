package pl.doz.kryptoapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.doz.kryptoapp.adapter.CryptoAdapter
import pl.doz.kryptoapp.databinding.CryptoMainBinding
import pl.doz.kryptoapp.model.CryptoModel
import pl.doz.kryptoapp.model.toCryptoModel
import pl.doz.kryptoapp.viewmodel.CryptoState
import pl.doz.kryptoapp.viewmodel.CryptoViewModel


@AndroidEntryPoint
class CryptoFragment : Fragment() {
    private lateinit var _binding: CryptoMainBinding
    private val binding get() = _binding
    lateinit var cryptoAdapter: CryptoAdapter
    private val cryptoViewModel: CryptoViewModel by viewModels()
    val adapter = CryptoAdapter { navigateToCoin(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CryptoMainBinding.inflate(layoutInflater, container, false)
        with(binding) {
            cryptoList.adapter = adapter
            cryptoList.layoutManager = GridLayoutManager(context, 2)
            viewModel = cryptoViewModel
            cryptoViewModel.getCrypto()
            selectCrypto.addTextChangedListener(object  : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filterRecyclerView(s.toString())
                }
            })
        }

        lifecycleScope.launch {
            cryptoViewModel.cryptoState.collect { state ->
                when (state) {
                    is CryptoState.Start -> {}
                    is CryptoState.Loading -> {}
                    is CryptoState.Success -> {
                        adapter.addData(state.cryptoList.map { it.toCryptoModel() })
                    }
                    is CryptoState.Failure -> {}
                }
            }
        }

        return binding.root
    }

    private fun navigateToCoin(crypto: CryptoModel) {
        findNavController().navigate(CryptoFragmentDirections.actionCryptoFragmentToCoinViewFragment(crypto.id))
    }

    private fun filterRecyclerView(query: String) {
        adapter.filter(query)
    }
}
