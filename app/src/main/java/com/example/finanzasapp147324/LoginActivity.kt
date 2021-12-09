package com.example.finanzasapp147324

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

const val RC_SIGN_IN = 343
const val COD_LOGOUT = 123
class LoginActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        auth = Firebase.auth
        sign_in_button.setSize(SignInButton.SIZE_WIDE)

        sign_in_button.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }

        btn_login.setOnClickListener {
         //   val intent = Intent(this, MainActivity::class.java)
            // start your next activity
           // startActivity(intent)
            valida_ingreso()
        }

        btn_loginRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistrarseActivity::class.java)
            // start your next activity
            startActivity(intent)
        }


    }
    private fun valida_ingreso() {
        var correo: String = et_emailLogin.text.toString()
        var contra: String = et_passwordLogin.text.toString()

        if (!correo.isNullOrBlank() && !contra.isNullOrBlank()) {
            ingresaFirebase(correo, contra)
        } else {
            Toast.makeText(
                this, "Error",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun ingresaFirebase(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Error ",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
         }

        if(requestCode == COD_LOGOUT){
            signOut()
        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void?> {
                Toast.makeText(this,"Sesion terminada", Toast.LENGTH_SHORT).show()
            })
    }


    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =  completedTask.getResult(ApiException::class.java)

            updateUI(account)
        } catch (e: ApiException) {
           // Log.w("test_signin", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {

        if (account != null){
            val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("name", account.getDisplayName())
            intent.putExtra("email", account.getEmail())
            startActivityForResult(intent,COD_LOGOUT)
        }
    }

}






