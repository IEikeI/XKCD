package com.haufe.einarbeitung.xkcd.model

/**
 * Wrapper für ViewPost zusammen mit dem eingetragenen "Gefällt mir!"
 */
class LikedComicModel(private var likedModel: Pair<Boolean, ComicModel?>) {

    fun getLikedModel(): Pair<Boolean, ComicModel?> {
        return likedModel
    }

    fun setLiked() {
        this.likedModel = likedModel.copy(first = true)
    }

    fun setUnliked() {
        this.likedModel = likedModel.copy(first = false)
    }

    fun isLiked() : Boolean {
        return this.likedModel.first
    }

    fun getComic() : ComicModel? {
        return this.likedModel.second
    }

    override fun toString(): String {
        return "LikedModel(likedModel=$likedModel)"
    }
}