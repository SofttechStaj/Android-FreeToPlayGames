package com.example.free_games

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.free_games.HomeRecyclerView.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class FragmentRegister : Fragment()
{
    private lateinit var EmailField: EditText
    private lateinit var UsernameField: EditText
    private lateinit var PasswordField: EditText
    //private lateinit var GenreField: EditText
    private lateinit var SignIn: Button
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var HomeFragment: Fragment
    private var email = ""
    private var username = ""
    private var password = ""
    private var genre = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.activity_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        EmailField = view.findViewById(R.id.register_email)
        UsernameField = view.findViewById(R.id.register_username)
        PasswordField = view.findViewById(R.id.register_password)
        //GenreField = view.findViewById(R.id.register_genre)
        SignIn = view.findViewById(R.id.button_sign_in)
        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account")
        progressDialog.setCanceledOnTouchOutside(false)

        SignIn.setOnClickListener{
            validateData()
        }
    }

    private fun validateData()
    {
        email = EmailField.text.toString().trim()
        username = UsernameField.text.toString().trim()
        password = PasswordField.text.toString().trim()
        //genre = GenreField.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailField.error = "invalid email"
        }
        else if (TextUtils.isEmpty(password))
        {
            PasswordField.error = "Please enter password"
        }
        else if(password.length < 6)
        {
            PasswordField.error = "Password must be at least 6 characters long"
        }
        else
        {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //val firebaseUser = firebaseAuth.currentUser
                Toast.makeText(context, "Registration is sucessfull", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
                getFragmentManager()?.popBackStack()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                //Log.e("Tag", "on failure working: $e")
                Toast.makeText(context, "Regsitration failed: $e", Toast.LENGTH_SHORT).show()
            }
    }
}