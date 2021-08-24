package com.example.free_games.HomeRecyclerView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.free_games.API.RetrofitInstance
import com.example.free_games.models.GameModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerActivityViewModel: ViewModel() {
    var data: MutableLiveData<List<GameModel>>
    //var randomizedListData: MutableLiveData<List<GameModel>>
    var postlist1: MutableList<GameModel>
    var postlist2: MutableList<GameModel>

    init {
        data = MutableLiveData()
        postlist1 = mutableListOf()
        postlist2 = mutableListOf()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<List<GameModel>>
    {
        makeApiCall()
        return data
    }

    fun sortData(sort: Boolean)
    {

        if (sort == true)
        {
            data.postValue(postlist2)
        }
        else
        {
            data.postValue(postlist1)
        }
        Log.e("sortData", data.toString())
    }

    fun makeApiCall()
    {
        var API = RetrofitInstance.api
        var call = API.posts
        call.enqueue(object: Callback<List<GameModel>> {
            override fun onResponse(call: Call<List<GameModel>>, response: Response<List<GameModel>>)
            {
                postlist1 = (response.body() as List<GameModel>).toMutableList()
                postlist2 = (response.body() as List<GameModel>).toMutableList()
                postlist1.shuffle()
                data.postValue(postlist1)
                postlist2.sortByDescending { it.release_date }
//                randomizedListData.postValue(postlist2)
            }
            override fun onFailure(call: Call<List<GameModel>>, t: Throwable)
            {
                data.postValue(null)
            }
        })
    }
}


