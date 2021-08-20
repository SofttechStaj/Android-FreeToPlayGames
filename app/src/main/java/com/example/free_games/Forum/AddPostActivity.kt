package com.example.free_games.Forum

import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.Color.BLACK
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.free_games.R
import com.example.free_games.databinding.ActivityAddPostBinding
import com.example.free_games.databinding.ActivityGameDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class AddPostActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPostBinding
    private lateinit var progressDialog: ProgressDialog
    //private var title = ""
    //private var content = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPost.setOnClickListener{
           AddPostToFireStore()
        }
    }

    fun AddPostToFireStore()
    {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Posting Content")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()

        val DB = FirebaseFirestore.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

        val ForumPosts: MutableMap<String, Any> = HashMap()
        ForumPosts["comment_number"] = 0
        ForumPosts["date"] = simpleDateFormat.format(Date())
        ForumPosts["title"] = binding.titleEdittext.text.toString().trim()
        ForumPosts["username"] = "username"
        ForumPosts["view_number"] = 0
        DB.collection("ForumPosts")
            .add(ForumPosts)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val snackbar = Snackbar
                    .make(coordinator, "Content Posted sucesfully", Snackbar.LENGTH_LONG)
                snackbar.show()
                val handler = Handler()
                handler.postDelayed({
                    finish()
                }, 1500)
            }
            .addOnFailureListener{e ->
                Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
                finish()
            }

    }
}