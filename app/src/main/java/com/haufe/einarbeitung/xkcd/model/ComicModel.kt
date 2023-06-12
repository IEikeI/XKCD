package com.haufe.einarbeitung.xkcd.model

import kotlinx.serialization.*

@Serializable
data class ComicModel(val month: String,
                      val num /* postID */: Int,
                      val link: String,
                      val year: String,
                      val news: String,
                      val safe_title: String,
                      val transcript /* Text für TTS */: String,
                      val alt /* Alternativer Text für Bildbeschreibung */: String,
                      val img /* url */: String,
                      val title: String,
                      val day: String)
