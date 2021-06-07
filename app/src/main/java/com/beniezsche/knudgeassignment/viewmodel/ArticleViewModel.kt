package com.beniezsche.knudgeassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beniezsche.knudgeassignment.model.ArticleResponse
import com.beniezsche.knudgeassignment.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel: ViewModel() {

    fun getArticles(category: String) : MutableLiveData<ArticleResponse> {

        val data = MutableLiveData<ArticleResponse>()

        RetrofitClient.dataService.getArticles(category).enqueue(object : Callback<ArticleResponse> {
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<ArticleResponse>, articleResponse: Response<ArticleResponse>) {
                if (articleResponse.code() == 200){
                    data.value = articleResponse.body()
                }
            }
        })

        return data

    }
}