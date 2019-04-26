package com.example.pokedexkotlinfragment

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexkotlinfragment.Utilities.AppConstants
import com.example.pokedexkotlinfragment.Utilities.NetworkUtils
import com.example.pokedexkotlinfragment.Utilities.poke
import com.example.pokedexkotlinfragment.Utilities.pokeResults
import com.google.gson.Gson
import kotlinx.android.synthetic.main.cuadro_nombre_pokemon.*
import kotlinx.android.synthetic.main.fragment_fragmento_principal.*
import kotlinx.android.synthetic.main.fragment_fragmento_principal.view.*
import java.io.IOException
import java.net.URL

class InfoFragment : Fragment() {
    lateinit var lista_poke : ArrayList<pokeResults>
    lateinit var globalView : View
    lateinit var Rview : RecyclerView
    var ori: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ori = resources.configuration.orientation
        FetchPokemonTask().execute()
        globalView = inflater.inflate(R.layout.fragment_fragmento_principal, container, false)
        return globalView
    }

    inner class FetchPokemonTask : AsyncTask<String, Void, ArrayList<poke>>() {
        override fun doInBackground(vararg pokemonNumbers: String?): ArrayList<poke> {
            var todos  = ArrayList<poke>()
            lista_poke = ArrayList<pokeResults>()
            var g = Gson()
            var pokeAPI : URL = NetworkUtils.buildUrl()
            var result : String?

            try {
                result = NetworkUtils.getResponseFromHttpUrl(pokeAPI);
                var todosPokemon : poke = g.fromJson(result, poke::class.java)
                //lista_poke = todosPokemon.results
                for (i in 0 until todosPokemon.results.size) {
                    //todos.add(pokemon);
                    lista_poke.add(todosPokemon.results[i])
                }
            }catch ( e : IOException){
                e.printStackTrace()
            }
            return todos
        }

        override fun onPostExecute(result: ArrayList<poke>?) {
            if (result != null || !result.equals("")){
                initRecycler(ori)
            }
        }
        fun portraitView(lista : pokeResults){
            var newIntent = Intent(context, Main2Activity::class.java)
            newIntent.putExtra(AppConstants.INTENT_MESSAGE_KEY, lista.url)
            startActivity(newIntent)
        }

        fun initRecycler(ori : Int){
            var adapter : Pokemon_adapter?

            if(ori== Configuration.ORIENTATION_PORTRAIT){
                adapter = Pokemon_adapter(lista_poke, {pokemon : pokeResults -> portraitView(pokemon)})
            } else{
                adapter = Pokemon_adapter(lista_poke, {pokemon : pokeResults -> landscapeView(pokemon)})
            }

            globalView.reciclador.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }

        fun landscapeView(lista : pokeResults){
            var mainFrag = MainFragment.newInstance(lista.url)
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.informacion_pokemon, mainFrag).commit()

        }
    }

}
