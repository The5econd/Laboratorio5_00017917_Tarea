package com.example.pokedexkotlinfragment.arraysInPokeInd.objectInPokeMoves

import com.example.pokedexkotlinfragment.arraysInPokeInd.objectInPokeMoves.objectInPokeMovesVGD.move_learn_method
import com.example.pokedexkotlinfragment.arraysInPokeInd.objectInPokeMoves.objectInPokeMovesVGD.version_group

data class poke_moves_VGD (
    var level_learned_at : Int,
    var move_learn_method: move_learn_method,
    var version_group: version_group
)