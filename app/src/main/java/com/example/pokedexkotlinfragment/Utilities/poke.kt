package com.example.pokedexkotlinfragment.Utilities

import java.util.ArrayList

data class poke (
    var count : Int,
    var next : String,
    var previous : String,
    var results : ArrayList<pokeResults>
    )