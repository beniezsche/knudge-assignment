package com.beniezsche.knudgeassignment.networking

import com.beniezsche.knudgeassignment.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.*

interface DataService {

    @GET("/v2/top-headlines?country=in&apiKey=29bcbd1b5467448bb849b5a3732ac28f")
    fun getArticles(@Query("category") category: String): Call<ArticleResponse>
}