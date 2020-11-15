package es.iessaladillo.pedrojoya.intents.ui.battle

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

private const val POKEMON1 = "POKEMON1"
private const val POKEMON2 = "POKEMON2"

class BattleActivityViewModel (savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _pokemon1 : MutableLiveData <Pokemon> = savedStateHandle.getLiveData(POKEMON1, Database.getRandomPokemon())
    val pokemon1: LiveData<Pokemon> get() = _pokemon1
    private val _pokemon2 : MutableLiveData <Pokemon> = savedStateHandle.getLiveData(POKEMON2, Database.getRandomPokemon())
    val pokemon2: LiveData<Pokemon> get() = _pokemon2

    fun fight(pokemon1: Pokemon, pokemon2: Pokemon) : Pokemon {
        return if(pokemon1.strenght > pokemon2.strenght){
            pokemon1
        }else{
            pokemon2
        }
    }

    fun result(intent: Intent) {
        if (!intent.hasExtra(SelectionActivity.SELECTEDPOKEMON) || !intent.hasExtra(SelectionActivity.SCREEN) ) {
            throw RuntimeException("BattleActivity must receive pokemonId and screen in result intent")
        }

        if (intent.getIntExtra(SelectionActivity.SCREEN, 1)==1){
            _pokemon1.value = intent.getParcelableExtra(SelectionActivity.SELECTEDPOKEMON)!!
        } else{
            _pokemon2.value = intent.getParcelableExtra(SelectionActivity.SELECTEDPOKEMON)!!
        }

    }

}