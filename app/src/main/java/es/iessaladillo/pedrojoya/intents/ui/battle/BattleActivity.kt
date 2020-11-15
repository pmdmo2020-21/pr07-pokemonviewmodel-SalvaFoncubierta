package es.iessaladillo.pedrojoya.intents.ui.battle

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivityViewModel
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivityViewModel

private const val RC_POKEMON_SELECTION: Int = 1

class BattleActivity : AppCompatActivity() {

    private lateinit var binding: BattleActivityBinding
    private lateinit var viewModel : BattleActivityViewModel

    private val selectionCall = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        val resultIntent = result.data
        if (result.resultCode == RESULT_OK && resultIntent != null) {
            extractResult(resultIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BattleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(BattleActivityViewModel::class.java)
        setUpViews()
    }

    private fun setUpViews() {
        binding.imgPokemon1.setImageDrawable(getDrawable(viewModel.pokemon1.value!!.idImage))
        binding.imgPokemon2.setImageDrawable(getDrawable(viewModel.pokemon2.value!!.idImage))
        binding.lblPokemon1.text = getText(viewModel.pokemon1.value!!.idName)
        binding.lblPokemon2.text = getText(viewModel.pokemon2.value!!.idName)
        binding.viewPokemon1.setOnClickListener { selectPokemon(viewModel.pokemon1.value!!, 1) }
        binding.viewPokemon2.setOnClickListener { selectPokemon(viewModel.pokemon2.value!!, 2) }
        binding.bttFight.setOnClickListener{ selectWinner() }
    }

    private fun selectPokemon(pokemon : Pokemon, screen : Int) {
        val intent = SelectionActivity.newIntent(this, pokemon, screen)
        selectionCall.launch(intent)
    }

    private fun selectWinner() {
        val winner = viewModel.fight(viewModel.pokemon1.value!!, viewModel.pokemon2.value!!)
        val intent = WinnerActivityViewModel.newIntent(this, winner)
        startActivityForResult(intent, RC_POKEMON_SELECTION)
    }

    private fun extractResult(intent: Intent) {
        viewModel.result(intent)
        binding.imgPokemon1.setImageDrawable(getDrawable(viewModel.pokemon1.value!!.idImage))
        binding.lblPokemon1.text = getText(viewModel.pokemon1.value!!.idName)
        binding.imgPokemon2.setImageDrawable(getDrawable(viewModel.pokemon2.value!!.idImage))
        binding.lblPokemon2.text = getText(viewModel.pokemon2.value!!.idName)
    }

}