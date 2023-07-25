package pl.doz.kryptoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.doz.kryptoapp.R
import pl.doz.kryptoapp.databinding.FragmentEntryBinding

@AndroidEntryPoint
class EntryFragment : Fragment() {

    private lateinit var _binding: FragmentEntryBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntryBinding.inflate(layoutInflater, container, false)

        _binding.coinButton.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_cryptoFragment)
        }
        return binding.root
    }
}