package com.haufe.einarbeitung.xkcd.model

class LikedComicModels {

    private var likedModels: MutableList<ComicModel> = mutableListOf<ComicModel>()

    fun add(index: Int, viewPostModel: ComicModel) {
        likedModels.add(index, viewPostModel)
    }

    fun removeAt(index: Int) {
        likedModels.removeAt(index)
    }

    fun remove(viewPostModel: ComicModel) {
        likedModels.remove(viewPostModel)
    }

    fun clear() {
        likedModels.clear()
    }

    fun size(): Int {
        return likedModels.size
    }

    fun contains(viewPostModel: ComicModel): Boolean  {
        return likedModels.contains(viewPostModel)
    }

    fun indexOf(viewPostModel: ComicModel): Int {
        return likedModels.indexOf(viewPostModel)
    }

    fun getModels(): MutableList<ComicModel> {
        return likedModels
    }

    override fun toString(): String {
        return "ViewPostModels(models=$likedModels)"
    }
}