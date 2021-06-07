package com.beniezsche.knudgeassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleResponse {

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null

}