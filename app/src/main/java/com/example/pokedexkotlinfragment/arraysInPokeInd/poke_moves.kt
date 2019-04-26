package com.example.pokedexkotlinfragment.arraysInPokeInd

import com.example.pokedexkotlinfragment.arraysInPokeInd.objectInPokeMoves.move
import com.example.pokedexkotlinfragment.arraysInPokeInd.objectInPokeMoves.poke_moves_VGD

data class poke_moves (
    var move : move,
    var version_group_details : ArrayList<poke_moves_VGD>
)