package es.iessaladillo.pedrojoya.intents.ui.winner

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding

class WinnerActivity : AppCompatActivity() {

    private lateinit var binding: WinnerActivityBinding
    private lateinit var viewModel: WinnerActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WinnerActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(WinnerActivityViewModel::class.java)
        viewModel.getIntentData(intent)
        setUpViews()
    }

    private fun setUpViews(){
        binding.imgWinner.setImageDrawable(getDrawable(viewModel.pokemon.value!!.idImage))
        binding.lblWinner.text = getText(viewModel.pokemon.value!!.idName)
    }

}