package com.haufe.einarbeitung.xkcd.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haufe.einarbeitung.xkcd.model.ComicModel
import com.haufe.einarbeitung.xkcd.model.LikedComicModel

class ComicViewModel(private var dataModel: LikedComicModel) : ViewModel() {

    /**
     * MutableLiveData für das ComicFragment, Likes
     * Wenn ein "Gefällt mir" geändert wird, so wird hier ein UI-Update ausgelöst
     */
    val uiLikeLiveData = MutableLiveData<Boolean>()

    /**
     * MutableLiveData für das ComicFragment, Texte abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val uiTextLiveData = MutableLiveData<String>()

    fun getUpdatedNumber() {
        val updatedNumber = dataModel.getComic().num.toString()
        uiTextLiveData.postValue(updatedNumber)
    }

    fun getUpdatedSafeName() {
        // TODO: "Sicherer Modus"
        val updatedSafeName = dataModel.getComic().safe_title
        uiTextLiveData.postValue(updatedSafeName)
    }

    fun getUpdatedName() {
        val updatedName = dataModel.getComic().title
        uiTextLiveData.postValue(updatedName)
    }

    fun getUpdatedDate() {
        val divider = "."
        val day = dataModel.getComic().day
        val month = dataModel.getComic().month
        val year =  dataModel.getComic().year
        val updatedDate = day + divider + month + divider + year
        uiTextLiveData.postValue(updatedDate)
    }

    fun getUpdatedLike() {
        val updatedText = dataModel.isLiked()
        uiLikeLiveData.postValue(updatedText)
    }

    fun getUpdatedTranskript() {
        val updatedTranscript = dataModel.getComic().transcript
        uiTextLiveData.postValue(updatedTranscript)
    }

}