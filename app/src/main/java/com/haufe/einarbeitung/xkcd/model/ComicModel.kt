package com.haufe.einarbeitung.xkcd.model

import kotlinx.serialization.Serializable

@Serializable
data class ComicModel(val month: String = "1",
                      val num /* postID */: Int = 1,
                      val link: String = "",
                      val year: String = "2023",
                      val news: String = "News",
                      val safe_title: String = "Safe Titel",
                      val transcript /* Text für TTS */: String = "Transcript",
                      val alt /* Alternativer Text für Bildbeschreibung */: String = "Alt",
                      val img /* url */: String = "",
                      val title: String = "Title",
                      val day: String = "1") : java.io.Serializable