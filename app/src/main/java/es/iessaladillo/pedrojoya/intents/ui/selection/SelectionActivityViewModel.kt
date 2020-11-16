package es.iessaladillo.pedrojoya.intents.ui.selection

import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class SelectionActivityViewModel : ViewModel() {

    private val _pokemon : MutableLiveData<Pokemon> = MutableLiveData(Database.getRandomPokemon())
    val pokemon: LiveData<Pokemon> get() = _pokemon

    fun getSelectedButton(array: Array <RadioButton>){
        for (compoundButton in array){
            if(compoundButton.tag == pokemon.value){
                compoundButton.isChecked = true
            }
        }
    }

    fun selectPokemon( radioButton: RadioButton, array: Array <RadioButton>) {
        radioButton.isChecked = true
        _pokemon.value = radioButton.tag as Pokemon
        for (compoundButton in array){
            if(compoundButton != radioButton){
                compoundButton.isChecked = false
            }
        }
    }

    fun changePokemon(pokemon: Pokemon) {
        _pokemon.value = pokemon
    }

}