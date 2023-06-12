package com.haufe.einarbeitung.xkcd.services

import com.google.gson.Gson
import com.haufe.einarbeitung.xkcd.model.ComicModel

class ViewPostParserJSON {

    companion object {
        /**
         * Wandelt den Rückgabestring der herunter gelandenen Datei in ein JSON-Object um
         * mithilfe von Gson
         */
        fun parseToObject(jsonString: String): ComicModel? {
            val gson = Gson()
            val viewPostModel: ComicModel? = gson.fromJson(jsonString, ComicModel::class.java)
            return viewPostModel
        }
    }

}