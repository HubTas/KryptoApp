package pl.doz.kryptoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pl.doz.kryptoapp.databinding.StartPageBinding

@AndroidEntryPoint
class EntryActivity : AppCompatActivity() {

    private lateinit var binding: StartPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
