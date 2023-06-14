package com.haufe.einarbeitung.xkcd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.haufe.einarbeitung.xkcd.model.LikedComicModel

class ComicViewModelFactory(likedComicModel: LikedComicModel) : ViewModelProvider.Factory {
    private var mDataModel: LikedComicModel? = likedComicModel

    fun setDataModel(dataModel: LikedComicModel) {
        this.mDataModel = dataModel
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComicViewModel(mDataModel!!) as T
    }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return ComicViewModel(mDataModel!!) as T
    }

}