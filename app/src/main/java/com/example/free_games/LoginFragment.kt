package com.example.free_games

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
import com.example.free_games.HomeRecyclerView.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment()
{
    private lateinit var EmailField : EditText
    private lateinit var PasswordField : EditText
    private lateinit var LoginButton : Button
    private lateinit var RegisterButton : Button
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var registerFragment: Fragment
    private lateinit var HomeFragment: Fragment
    private var email = ""
    private var password = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        EmailField = view.findViewById(R.id.email)
        PasswordField = view.findViewById(R.id.password)
        LoginButton = view.findViewById(R.id.login_button)
        RegisterButton = view.findViewById(R.id.register_button)

        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loging in")
        progressDialog.setCanceledOnTouchOutside(false)

        // init fiirebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        CheckUser()

        RegisterButton.setOnClickListener{
            registerFragment = FragmentRegister()
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.content_area, registerFragment)
            fr?.addToBackStack("Login")
            fr?.commit()
        }

        LoginButton.setOnClickListener{
            validateData()

        }


    }

    private fun validateData() {
        email = EmailField.text.toString().trim()
        password = PasswordField.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailField.error = "invalid email"

        }
        else if (TextUtils.isEmpty(password))
        {
            PasswordField.error = "Please enter password"
        }
        else
        {
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(context, "Login is sucessfull", Toast.LENGTH_SHORT).show()
                HomeFragment = RecyclerView()
                getFragmentManager()?.popBackStack()
            }
            .addOnFailureListener{e ->
                progressDialog.dismiss()
                Log.d("Log", e.toString())
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun CheckUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null)
        {
            Log.d("Log", "user already logged in")
        }
    }
}