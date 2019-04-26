package com.example.pokedexkotlinfragment.Utilities

import android.net.Uri
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    companion object {
        var POKEMON_API_BASE_URL:String  = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=807"

        fun buildUrl() : URL {
            var builtUri = Uri.parse(POKEMON_API_BASE_URL)
                    .buildUpon()
                    .build()

            lateinit var url : URL
            try {
                url = URL(builtUri.toString())
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }

            return url
        }

        fun getResponseFromHttpUrl(url:URL) : String?{
            var urlConnection : HttpURLConnection  =  url.openConnection() as HttpURLConnection;
            try {
                var ing : InputStream = urlConnection.inputStream

                var scanner = Scanner(ing)
                scanner.useDelimiter("\\A")

                var hasInput: Boolean = scanner.hasNext()
                if (hasInput) {
                    return scanner.next()
                } else {
                    return null
                }
            } finally {
                urlConnection.disconnect()
            }
        }

        fun getUrls(urlGet:String) : URL {
            var builtUri = Uri.parse(urlGet)
                    .buildUpon()
                    .build()
            lateinit var url : URL
            try {
                url = URL(builtUri.toString())
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return url
        }
    }
}