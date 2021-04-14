package com.carlostorres.virtusdigital.data.network.models.reponses

data class HighlightResult(
    val author: Author,
    val comment_text: CommentText,
    val story_title: StoryTitle,
    val story_url: StoryUrl,
    val title: Title,
    val url: Url
)