package com.example.pokedexkotlinfragment.arraysInPokeInd

import com.example.pokedexkotlinfragment.arraysInPokeInd.objectInPokeAbilities.ability

data class poke_abilities (
    var ability : ability,
    var is_hidden : Boolean,
    var slot : Int
)