package com.example.free_games.Forum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.free_games.HomeRecyclerView.TopSpacingItemDecoration
import com.example.free_games.databinding.ForumRecyclerviewBinding
import com.example.free_games.models.ForumModel
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.forum_recyclerview.*


class Forum : AppCompatActivity()
{
    private lateinit var Posts: ArrayList<ForumModel>
    private lateinit var forumAdapter: ForumAdapter
    lateinit var binding: ForumRecyclerviewBinding
    private lateinit var viewModel: ForumViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ForumRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        Posts = arrayListOf()

        viewModel = ViewModelProviders.of(this).get(ForumViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, {
            if (it != null)
            {
                forumAdapter.setData(it)
                forumAdapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show()
            }
        })

        binding.AddPostButton.setOnClickListener {
            val intent = Intent(this@Forum, AddPostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView()
    {
        binding.forumRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            forumAdapter = ForumAdapter()
            this.adapter = forumAdapter
            val topSpacingItemDecoration = TopSpacingItemDecoration(5)
            addItemDecoration(topSpacingItemDecoration)
        }
    }
}

