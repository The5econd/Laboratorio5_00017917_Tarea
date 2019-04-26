package com.example.pokedexkotlinfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedexkotlinfragment.Utilities.AppConstants

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var mainFrag = MainFragment.newInstance(intent.getStringExtra(AppConstants.INTENT_MESSAGE_KEY))
        supportFragmentManager.beginTransaction().replace(R.id.informacion_pokemon, mainFrag).commit()

    }
}
