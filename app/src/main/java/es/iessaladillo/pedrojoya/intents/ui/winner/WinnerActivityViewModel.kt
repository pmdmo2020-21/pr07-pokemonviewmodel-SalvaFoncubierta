package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

private const val POKEMON = "POKEMON"

class WinnerActivityViewModel (savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _pokemon : MutableLiveData<Pokemon> = savedStateHandle.getLiveData(POKEMON, Database.getRandomPokemon())
    val pokemon: LiveData<Pokemon> get() = _pokemon

    companion object {
        const val WINNERPOKEMON = "WINNERPOKEMON"

        fun newIntent(context: Context, pokemon: Pokemon): Intent =
            Intent(context, WinnerActivity::class.java)
                .putExtras(bundleOf(WINNERPOKEMON to pokemon))

    }

    fun getIntentData(intent : Intent) {
        if (!intent.hasExtra(WINNERPOKEMON)) {
            throw RuntimeException(
                "WinnerActivity needs to receive a pokemon as extra")
        }
        _pokemon.value = intent.getParcelableExtra(WINNERPOKEMON)!!
    }

}

