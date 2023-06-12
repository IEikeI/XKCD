package com.haufe.einarbeitung.xkcd.model


/**
 * Model, that holds a mutable list of ViewPostModels
 */
class ComicModels {
    private var models: MutableList<ComicModel> = mutableListOf<ComicModel>()

    fun add(index: Int, viewPostModel: ComicModel) {
        models.add(index, viewPostModel)
    }

    fun removeAt(index: Int) {
        models.removeAt(index)
    }

    fun remove(viewPostModel: ComicModel) {
        models.remove(viewPostModel)
    }

    fun clear() {
        models.clear()
    }

    fun size(): Int {
        return models.size
    }

    fun contains(viewPostModel: ComicModel): Boolean  {
        return models.contains(viewPostModel)
    }

    fun indexOf(viewPostModel: ComicModel): Int {
        return models.indexOf(viewPostModel)
    }

    fun getModels(): MutableList<ComicModel> {
        return models
    }

    override fun toString(): String {
        return "ViewPostModels(models=$models)"
    }

}
