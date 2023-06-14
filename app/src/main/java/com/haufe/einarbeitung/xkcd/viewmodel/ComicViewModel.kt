package com.haufe.einarbeitung.xkcd.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haufe.einarbeitung.xkcd.model.LikedComicModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

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
    val uiTitleLiveData = MutableLiveData<String>()

    /**
     * MutableLiveData für das ComicFragment, Texte abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val uiDateLiveData = MutableLiveData<String>()

    /**
     * MutableLiveData für das ComicFragment, Texte abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val uiAltLiveData = MutableLiveData<String>()

    /**
     * MutableLiveData für das ComicFragment, Texte abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val uiSafeTitleLiveData = MutableLiveData<String>()

    /**
     * MutableLiveData für das ComicFragment, Texte abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val uiTranscriptLiveData = MutableLiveData<String>()

    /**
     * MutableLiveData für das ComicFragment, URL abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val imageURLLiveData = MutableLiveData<String>()

    /**
     * MutableLiveData für das ComicFragment, Texte abgeleitet aus dem Comic-Model
     * Wenn Daten geupdated werden, so wird hier ein UI-Update ausgelöst
     */
    val uiNumberLiveData = MutableLiveData<Int>()

    fun getUpdatedNumber() {
        val updatedNumber = dataModel.getComic()!!.num
        uiNumberLiveData.postValue(updatedNumber)
    }

    fun getUpdatedSafeName() {
        // TODO: "Sicherer Modus"?
        val updatedSafeName = dataModel.getComic()!!.safe_title
        uiSafeTitleLiveData.postValue(updatedSafeName)
    }

    fun getUpdateAlt() {
        val updatedSafeName = dataModel.getComic()!!.alt
        uiAltLiveData.postValue(updatedSafeName)
    }

    fun getUpdatedTitle() {
        val updatedName = dataModel.getComic()!!.title
        uiTitleLiveData.postValue(updatedName)
    }

    fun getUpdatedDate() {
        val tag = "# "
        val number = dataModel.getComic()!!.num.toString()
        val divider = "."
        val day = dataModel.getComic()!!.day
        val month =  dataModel.getComic()!!.month
        val year = dataModel.getComic()!!.year
        val updatedDate = day + divider + month + divider + year
        val fullText = tag + number + "   of " + updatedDate
        uiDateLiveData.postValue(fullText)
    }

    fun getUpdatedLike() {
        val updatedText = dataModel.isLiked()
        uiLikeLiveData.postValue(updatedText)
    }

    fun getUpdatedTranscript() {
        val updatedTranscript = dataModel.getComic()!!.transcript
        uiTranscriptLiveData.postValue(updatedTranscript)
    }

    fun getUpdatedImageURL() {
        val imageURL = dataModel.getComic()!!.img
        imageURLLiveData.postValue(imageURL)
    }

    /**
     * Gibt das aktuelle Datenmodel zurück
     */
    fun getDataModel() : LikedComicModel {
        return dataModel
    }

    /**
     * Updated das DataModel
     */
    fun setDataModel(dataModel: LikedComicModel) {
        this.dataModel = dataModel
    }
}