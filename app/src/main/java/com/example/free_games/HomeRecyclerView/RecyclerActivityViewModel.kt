package com.example.free_games.HomeRecyclerView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.free_games.API.RetrofitInstance
import com.example.free_games.models.GameModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerActivityViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<List<GameModel>>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<List<GameModel>>
    {
        makeApiCall()
        return recyclerListData
    }

    fun makeApiCall()
    {
        var API = RetrofitInstance.api
        var call = API.posts
        call.enqueue(object: Callback<List<GameModel>> {
            override fun onResponse(call: Call<List<GameModel>>, response: Response<List<GameModel>>)
            {
                 var postlist: List<GameModel> = response.body() as List<GameModel>
                 recyclerListData.postValue(postlist)
            }
            override fun onFailure(call: Call<List<GameModel>>, t: Throwable)
            {
                recyclerListData.postValue(null)
            }
        })
    }
}


