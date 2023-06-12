package com.haufe.einarbeitung.xkcd.services

import org.json.JSONException
import org.json.JSONObject

class DownloadService {

    companion object {
        /**
         * Gleichbleibender Anfangsteil der Ziel-URL
         */
        val baseURL: String = "https://xkcd.com/"

        /**
         * Gleichbleibendes Ende (inklusive Dateiformat) der Ziel-URL
         */
        val urlPostFix: String = "/info.0.json"

        /**
         * URL des aktuellen Comis (wird bnötigt, um die Gesamtanzahl heraus zu bekommen und das aktuelle direkt beim
         * Start der App anzuzeigen.
         */
        val currentComicURL: String = "https://xkcd.com/info.0.json"

        /**
        * Statische Methode, um JSON-Informationen von der entsprechenden xkcd-Schnittstelle abzurufen
         */
        fun getPostFromServerVia(jsonID: String): JSONObject? {
            var jsonObject: JSONObject? = null
            try {
                val fullURL: String = baseURL + jsonID + urlPostFix
                jsonObject = JSONObject(fullURL)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return jsonObject
        }

        /**
         * Statische Methode. Ruft die JSON-Informationen des aktuellen Comics ab
         */
        fun getCurrentComic(): JSONObject? {
            var jsonObject: JSONObject? = null
            try {
                val fullURL: String = currentComicURL
                jsonObject = JSONObject(fullURL)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return jsonObject
        }
    }

}