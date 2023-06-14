package com.haufe.einarbeitung.xkcd.repository

import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.haufe.einarbeitung.xkcd.model.ComicModels

class ComicRepository  (var requestQueue: RequestQueue) {
    var posts = MutableLiveData<ComicModels>()

    fun getData(maxNumber: Int): ComicModels {
        var comicModels: ComicModels = ComicModels()

        // TODO
        /*for ( i in maxNumber) {
            comicModels.add(i, )
        }*/
        return comicModels
    }

    private fun fetchAllComics() {
        //TODO fetch all JSON Daten with an iterator
        /*
        val jsonArrayRequest = JsonArrayRequest(
            for(i in 0) {
                ...
            }
            // TODO: Handle error

        )
        requestQueue.add(jsonArrayRequest)*/
    }
}