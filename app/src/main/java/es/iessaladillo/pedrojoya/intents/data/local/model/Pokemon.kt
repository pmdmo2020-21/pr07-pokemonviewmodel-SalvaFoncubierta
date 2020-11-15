package es.iessaladillo.pedrojoya.intents.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO: Define las propiedades de un pokemon
@Parcelize
data class Pokemon (val id : Long, val idImage : Int, val idName : Int, val strenght : Int) : Parcelable