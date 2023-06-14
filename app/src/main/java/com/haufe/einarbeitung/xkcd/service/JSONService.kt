package com.haufe.einarbeitung.xkcd.service

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.haufe.einarbeitung.xkcd.MainApplication
import com.haufe.einarbeitung.xkcd.model.ComicModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class JSONService {

    companion object {
        /**
         * Gleichbleibender Anfangsteil der Ziel-URL
         */
        private const val baseURL: String = "https://xkcd.com/"

        /**
         * Gleichbleibendes Ende (inklusive Dateiformat) der Ziel-URL
         */
        private const val urlPostFix: String = "/info.0.json"

        /**
         * URL des aktuellen Comis (wird bnötigt, um die Gesamtanzahl heraus zu bekommen und das aktuelle direkt beim
         * Start der App anzuzeigen.
         */
        private const val currentComicURL: String = "https://xkcd.com/info.0.json"

        /**
         * Gibt JSON-Informationen als String für eine
         * übergebene URL zurück.
         * Im Fehlerfall werden lokale Default-Informationen aufgerufen
         */
        private fun getJSONAsString(url: String): String? {
            var responseString: String? = null
            try {
                val apiResponse: String = URL(url).readText()
                val jsonObject: JSONObject = JSONObject(apiResponse)
                responseString = jsonObject.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return responseString
        }

        /**
         * Statische Methode: Gibt JSON-Informatioen als String für lokal
         * gespeicherte JSON-Dateien zurück.
         */
        private fun getJSONFromAsset(context: Context, fileName: String): String {
            var jsonString: String = ""
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return jsonString
        }

        /**
         * Ruft JSON-Informationen für Default-Comic ab
         * Fallback: Für den Fall, dass keine Internerverbindung besteht oder keine
         * Inhalte geladen werden können
         */
        private fun getDefaultComic(): String {
            val context: Context = MainApplication.instance.applicationContext
            val fileName: String = "data/defaultJSON.json"
            val jsonString = this.getJSONFromAsset(context, fileName)
            return jsonString
        }

        /**
         * Ruft JSON-Informationen für Comic mit übergebener ID ab
         */
        private fun getComicFromServer(jsonID: String): String {
            val fullURL: String = baseURL + jsonID + urlPostFix
            var jsonString = this.getJSONAsString(fullURL)
            if (jsonString == null) {
                jsonString = this.getDefaultComic()
            }
            return jsonString
        }

        /**
         * Ruft JSON-Informationen für Comic mit übergebener ID ab
         */
        private fun getCurrentComicFromServer(): String {
            val url: String = this.currentComicURL
            var jsonString: String? = this.getJSONAsString(url)
            /* Fallback, wenn Serverdaten nicht geladen werden können */
            if (jsonString == null) {
                jsonString = this.getDefaultComic()
            }
            return jsonString
        }

        /**
         * Wandelt den Rückgabe-String der herunter gelandenen Datei in ein JSON-Object um,
         * mithilfe von Gson
         */
        private fun parseToObject(jsonString: String): ComicModel {
            val gson = Gson()
            val type = object : TypeToken<ComicModel>() { }.type
            val viewPostModel: ComicModel = gson.fromJson(jsonString, type)
            return viewPostModel
        }

        /**
         * Ruft das aktuelle Comic ab
         */
        fun getCurrentComic() : ComicModel {
            val jsonResponse: String = this.getCurrentComicFromServer()
            val comic: ComicModel = this.parseToObject(jsonResponse)
            return comic
        }

        /**
         * Ruft ein Comic für eine ID ab
         */
        fun getComicFor(jsonID: String) : ComicModel {
            val jsonResponse: String = this.getComicFromServer(jsonID)
            val comic: ComicModel = this.parseToObject(jsonResponse)
            return comic
        }
    }

}