package com.example.free_games.API

import com.example.free_games.models.GameModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @get:GET("/api/games")
    val posts : Call<List<GameModel>>
}