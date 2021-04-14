package com.carlostorres.virtusdigital.data.network.models.reponses

data class Author(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)