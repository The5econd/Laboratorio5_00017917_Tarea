package com.example.pokedexkotlinfragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexkotlinfragment.Utilities.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_fragmento_info_pokemon.*
import kotlinx.android.synthetic.main.fragment_fragmento_principal.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import java.util.ArrayList


class MainFragment : Fragment() {
    var flag : Int = 0
    lateinit var nombre : String ; lateinit var tipoInd : String;lateinit var peso : String ;lateinit var altura : String; lateinit var tipo1 :String; lateinit var tipo2 : String; lateinit var tipos : String
    companion object {
        fun newInstance(url: String): MainFragment {
            var mainInstancia = MainFragment()
            mainInstancia.url = url
            return mainInstancia
        }
    }

    lateinit var url: String
    lateinit var globalView : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FetchPokemonInfoTask().execute()
        globalView = inflater.inflate(R.layout.fragment_fragmento_info_pokemon, container, false)
        return globalView
    }

    inner class FetchPokemonInfoTask : AsyncTask<String, Void, ArrayList<ArrayList<*>>>() {
        override fun doInBackground(vararg params: String?): ArrayList<ArrayList<*>> {
            val todasListas = ArrayList<ArrayList<*>>()
            val listaSTATS = ArrayList<String>()
            val listaHABILIDADES = ArrayList<String>()
            val listaImagen = ArrayList<String>()
            var g = Gson()
            var urlDatosPokemon : URL = NetworkUtils.getUrls(url)
            var result : String?

            try {
                result = NetworkUtils.getResponseFromHttpUrl(urlDatosPokemon)
                var infoDePokemon : pokeInd = g.fromJson(result, pokeInd::class.java)
                if (!infoDePokemon.sprites.front_default.isNullOrEmpty()) {
                    listaImagen.add( infoDePokemon.sprites.front_default)
                }
                /////////////////////////////DATOS DE LOS POKEMONES//////////////////
                nombre = infoDePokemon.name
                peso = (infoDePokemon.weight/10).toString()
                for (i in 0 until infoDePokemon.types.size) {
                    if (infoDePokemon.types.size === 2) {
                        tipo1 = infoDePokemon.types[0].type.name
                        tipo2 = infoDePokemon.types[1].type.name
                        tipos = tipo1 + "/" + tipo2
                        flag = 2
                    } else {
                        tipoInd = infoDePokemon.types[i].type.name
                        flag = 1
                    }
                }
                altura = (infoDePokemon.height*10).toString()

                ////////////////////////STATS///////////////////////////////////////
                for (i in 0 until infoDePokemon.stats.size) {
                    val statIndividual : String = infoDePokemon.stats[i].base_stat.toString()
                    listaSTATS.add(statIndividual)
                }

                ///////////////////////MOVIMIENTOS//////////////////////////////////
                for(i in 0 until infoDePokemon.moves.size){
                    val habilidadIndividual : String = infoDePokemon.moves[i].move.name
                    listaHABILIDADES.add(habilidadIndividual)
                }

                todasListas.add(listaHABILIDADES)
                todasListas.add(listaSTATS)
                todasListas.add(listaImagen)
            }catch ( e : IOException){
                e.printStackTrace()
            }
            return todasListas
        }
        override fun onPostExecute(pokemonListOfLists: ArrayList<ArrayList<*>>?) {
            if (pokemonListOfLists != null || !pokemonListOfLists.equals("")){
                new_window_pk_name.text = "NAME: " + nombre
                new_window_pk_weight.text = "WEIGHT: " + peso + " kg"
                if (flag == 1){
                    new_window_pk_type.text = "TYPE: " + tipoInd
                } else{
                    new_window_pk_type.text = "TYPES: " + tipos
                }
                new_window_pk_height.text = "HEIGHT: " + altura + " cm"

                ///////////////////COLOCANDO MOVIMIENTOS///////////////////////////////////
                for(i in 0 until pokemonListOfLists!![0].size){
                    var habilidad : String = pokemonListOfLists[0][i].toString()
                    var habi: String = new_window_pk_abilities.text.toString()
                    new_window_pk_abilities.text = habi + habilidad + "\n"
                }
                ///////////////////COLOCANDO STATS///////////////////////////////////
                new_window_pk_speed.text = pokemonListOfLists[1][0].toString()
                new_window_pk_specialDefense.text = pokemonListOfLists[1][1].toString()
                new_window_pk_specialAtack.text = pokemonListOfLists[1][2].toString()
                new_window_pk_defense.text = pokemonListOfLists[1][3].toString()
                new_window_pk_atack.text = pokemonListOfLists[1][4].toString()
                new_window_pk_hp.text = pokemonListOfLists[1][5].toString()

                //////////////////////COLOCANDO IMAGENES/////////////////////////////////
                Glide.with(globalView).load(pokemonListOfLists[2][0]).placeholder(R.mipmap.pikachu_icon).into(imagen_pokemon)
                Log.d("imagen", pokemonListOfLists[2][0].toString())
            }
        }
    }
}

