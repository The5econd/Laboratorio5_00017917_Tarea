package com.example.pokedexkotlinfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexkotlinfragment.Utilities.pokeResults
import kotlinx.android.synthetic.main.cuadro_nombre_pokemon.view.*
import java.util.ArrayList

class Pokemon_adapter (var listaPokemones: ArrayList<pokeResults>, val clickListener: (pokeResults) -> Unit): RecyclerView.Adapter<Pokemon_adapter.ViewHolderDatos>() {
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolderDatos {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.cuadro_nombre_pokemon, null, false)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolderDatos(view)
    }
    override fun getItemCount(): Int = listaPokemones.size

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) = holder.bin(listaPokemones[position], clickListener)

    inner class ViewHolderDatos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var nombre : Button

        fun bin(item : pokeResults, clickListener: (pokeResults) -> Unit) = with(itemView){

            nombre = itemView.findViewById(R.id.nombre_pokemon_boton)
            nombre.text = item.name
            nombre.setOnClickListener { clickListener(item) }
            var urlll : String = item.url
            var numero = ""
            if(urlll.length == 36) {
                numero = urlll.substring(urlll.length-2, urlll.length-1)
                Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + "${numero}" + ".png").into(imagen_en_lista)
            }
            if (urlll.length == 37){
                numero = urlll.substring(urlll.length-3, urlll.length-1)
                Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + "${numero}" + ".png").into(imagen_en_lista)
            }
            if(urlll.length == 38){
                numero = urlll.substring(urlll.length-4, urlll.length-1)
                Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + "${numero}" + ".png").into(imagen_en_lista)
            }
            Log.d("tamanio", numero)
            return@with 

            //https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
        }
    }
}