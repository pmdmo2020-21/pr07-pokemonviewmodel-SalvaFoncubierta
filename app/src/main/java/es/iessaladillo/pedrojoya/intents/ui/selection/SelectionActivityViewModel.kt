package es.iessaladillo.pedrojoya.intents.ui.selection

import android.widget.RadioButton
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

class SelectionActivityViewModel : ViewModel() {

    var pokemon : Pokemon? = null

    fun getSelectedButton(array: Array <RadioButton>){
        for (compoundButton in array){
            if(compoundButton.tag == pokemon){
                compoundButton.isChecked = true
            }
        }
    }

    fun selectPokemon( radioButton: RadioButton, array: Array <RadioButton>) {
        radioButton.isChecked = true
        pokemon = radioButton.tag as Pokemon
        for (compoundButton in array){
            if(compoundButton != radioButton){
                compoundButton.isChecked = false
            }
        }
    }

}