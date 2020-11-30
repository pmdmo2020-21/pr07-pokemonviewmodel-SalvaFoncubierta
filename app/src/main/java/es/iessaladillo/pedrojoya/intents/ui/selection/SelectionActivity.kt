package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.intents.data.local.Database.getPokemonById
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

class SelectionActivity : AppCompatActivity() {

    companion object {

        const val SELECTEDPOKEMON = "SELECTEDPOKEMON"
        const val SCREEN = "SCREEN"

        fun newIntent(context: Context, pokemon: Pokemon, screen: Int): Intent =
            Intent(context, SelectionActivity::class.java)
                .putExtras( bundleOf(SELECTEDPOKEMON to pokemon, SCREEN to screen))
    }

    private lateinit var binding: SelectionActivityBinding
    private lateinit var buttons: Array <RadioButton>
    private var screen = 1
    private lateinit var viewModel: SelectionActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectionActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SelectionActivityViewModel::class.java)
        getIntentData()
        setUpViews()
    }

    private fun setUpViews() {
        binding.rdbttPikachu.tag = getPokemonById(0)
        binding.rdbttGiratina.tag = getPokemonById(1)
        binding.rdbttCubone.tag = getPokemonById(2)
        binding.rdbttGyarados.tag = getPokemonById(3)
        binding.rdbttFeebas.tag = getPokemonById(4)
        binding.rdbttBulbasaur.tag = getPokemonById(5)

        buttons = arrayOf(binding.rdbttBulbasaur, binding.rdbttCubone, binding.rdbttPikachu,
            binding.rdbttGiratina, binding.rdbttGyarados, binding.rdbttFeebas)

        viewModel.getSelectedButton(buttons)

        binding.rdbttFeebas.setOnClickListener { viewModel.selectPokemon(binding.rdbttFeebas, buttons) }
        binding.rdbttGyarados.setOnClickListener { viewModel.selectPokemon(binding.rdbttGyarados, buttons) }
        binding.rdbttGiratina.setOnClickListener { viewModel.selectPokemon(binding.rdbttGiratina, buttons) }
        binding.rdbttPikachu.setOnClickListener { viewModel.selectPokemon(binding.rdbttPikachu, buttons) }
        binding.rdbttBulbasaur.setOnClickListener { viewModel.selectPokemon(binding.rdbttBulbasaur, buttons) }
        binding.rdbttCubone.setOnClickListener { viewModel.selectPokemon(binding.rdbttCubone, buttons) }

    }

    override fun onBackPressed() {
        val result = Intent().putExtras(bundleOf(SELECTEDPOKEMON to viewModel.pokemon, SCREEN to screen))
        setResult(RESULT_OK, result)
        super.onBackPressed()
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(SELECTEDPOKEMON)) {
            throw RuntimeException(
                "SelectionActivity needs to receive pokemon id as extra")
        }
        screen = intent.getIntExtra(SCREEN,1)
        viewModel.pokemon = intent.getParcelableExtra(SELECTEDPOKEMON)
    }

}