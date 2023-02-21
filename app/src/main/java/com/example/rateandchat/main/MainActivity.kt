package com.example.rateandchat.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.rateandchat.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    lateinit var googleLoInTextView: TextView
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        auth = FirebaseAuth.getInstance()

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        //Log in with google
        googleLoInTextView = findViewById(R.id.googleLogIn)
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,options)


        if (auth.currentUser != null){
            goToDashboardActivity()
        }
    }

    fun signInGoogle(view : View){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
     private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
         result ->
                if (result.resultCode == Activity.RESULT_OK){
                    val task = GoogleSignIn.getSignedInAccountFromIntent((result.data))
                    handleResults(task)
                }
     }
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account: GoogleSignInAccount? = task.result
            if (account!= null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful){
                val intent : Intent = Intent(this, DashBoardActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            } }
    }

    fun createButton (view : View){
        val intent = Intent(this, CreateUserActivity::class.java)
        startActivity(intent)
    }

    fun loginButton (view : View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goToDashboardActivity(){
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }
}