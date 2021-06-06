package br.com.lumean.enews.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializerUser {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.fluo.work/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    
    fun AccountService(): AccountService{
        return retrofit.create(AccountService::class.java)
    }
}