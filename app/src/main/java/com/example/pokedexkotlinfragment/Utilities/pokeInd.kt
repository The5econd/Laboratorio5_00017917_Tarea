package com.example.pokedexkotlinfragment.Utilities

import com.example.pokedexkotlinfragment.arraysInPokeInd.*
import com.example.pokedexkotlinfragment.objectsInPokeInd.Species
import com.example.pokedexkotlinfragment.objectsInPokeInd.Sprites

data class pokeInd (
    var abilities : ArrayList<poke_abilities>,
    var base_experience : Int,
    var forms : ArrayList<poke_form>,
    var game_indices : ArrayList<poke_indices>,
    var height : Int,
    var held_items : ArrayList<poke_held_items>,
    var id : Int,
    var is_default : Boolean,
    var location_area_encounters : String,
    var moves : ArrayList<poke_moves>,
    var name : String,
    var order : Int,
    var species : Species,
    var sprites : Sprites,
    var stats : ArrayList<poke_stats>,
    var types : ArrayList<poke_types>,
    var weight : Int
    )