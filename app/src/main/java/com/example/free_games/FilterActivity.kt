package com.example.free_games

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.free_games.HomeRecyclerView.RecyclerAdapter
import com.example.free_games.databinding.FilterActivityBinding


class FilterActivity : AppCompatActivity() {
    lateinit var binding: FilterActivityBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = FilterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var checkboxList = listOf(
            binding.ActionRPG,
            binding.BattleRoyale,
            binding.CardGame,
            binding.Fantasy,
            binding.FightingGame,
            binding.MMO,
            binding.MMORPG,
            binding.MOBA,
            binding.RPG,
            binding.Racing,
            binding.Shooter,
            binding.Strategy,
            binding.Social,
            binding.Sports)

        val homeActivityData = intent.getStringExtra("Checkboxes")
        var checkedList = homeActivityData!!.subSequence(1, homeActivityData.length).split(",")
        initializeCheckboxes(checkedList)
        binding.FilterButton.setOnClickListener{
            checkboxHandler()
        }

        binding.All.setOnClickListener{
            if(binding.All.isChecked)
            {
                checkboxList.forEach {
                    it.isChecked = false
                }
            }
        }

        checkboxList.forEach{
            it.setOnClickListener{
                binding.All.isChecked = false
            }
        }

    }

    fun initializeCheckboxes(checkedList: List<String>)
    {
        if ("All" !in checkedList) { binding.All.isChecked = false }
        if ("ARPG" in checkedList) { binding.ActionRPG.isChecked = true }
        if ("Battle Royale" in checkedList) { binding.BattleRoyale.isChecked = true }
        if ("Card Game" in checkedList) { binding.CardGame.isChecked = true }
        if ("Fantasy" in checkedList) { binding.Fantasy.isChecked = true }
        if ("Fighting" in checkedList) { binding.FightingGame.isChecked = true }
        if ("MMO" in checkedList) { binding.MMO.isChecked = true }
        if ("MMORPG" in checkedList) { binding.MMORPG.isChecked = true }
        if ("MOBA" in checkedList) { binding.MOBA.isChecked = true }
        if ("RPG" in checkedList) { binding.RPG.isChecked = true }
        if ("Racing" in checkedList) { binding.Racing.isChecked = true }
        if ("Shooter" in checkedList) { binding.Shooter.isChecked = true }
        if ("Strategy" in checkedList) { binding.Strategy.isChecked = true }
        if ("Social" in checkedList) { binding.Social.isChecked = true }
        if ("Sports" in checkedList) { binding.Sports.isChecked = true }
    }

    private fun checkboxHandler()
    {
        var list=""
        if(binding.All.isChecked) { list = "All" }
        else
        {
            if (binding.ActionRPG.isChecked) { list = "$list,Action RPG,ARPG" }
            if (binding.BattleRoyale.isChecked) { list = "$list,Battle Royale" }
            if (binding.CardGame.isChecked) { list = "$list,Card,Card Game" }
            if (binding.Fantasy.isChecked) { list = "$list,Fantasy" }
            if (binding.FightingGame.isChecked) { list = "$list,Fighting" }
            if (binding.MMO.isChecked) { list = "$list,MMO" }
            if (binding.MMORPG.isChecked) { list = "$list,MMORPG, MMORPG" }
            if (binding.MOBA.isChecked) { list = "$list,MOBA" }
            if (binding.RPG.isChecked) { list = "$list,RPG" }
            if (binding.Racing.isChecked) { list = "$list,Racing" }
            if (binding.Shooter.isChecked) { list = "$list,Shooter" }
            if (binding.Strategy.isChecked) { list = "$list,Strategy" }
            if (binding.Social.isChecked) { list = "$list,Social" }
            if (binding.Sports.isChecked) { list = "$list,Sports" }
        }
        Log.e("list from filter act", list)
        val returnIntent = Intent()
        returnIntent.putExtra("result", list)
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}