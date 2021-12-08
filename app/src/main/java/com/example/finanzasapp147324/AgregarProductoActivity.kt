package com.example.finanzasapp147324

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_agregar_producto.*

class AgregarProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)
        val db = Firebase.firestore


        btn_guardarProducto.setOnClickListener {
                val monto = textfieldMonto.text.toString()
                val nota = tv_agregarnombreproducto.text.toString()
            // Create a new user with a first and last name
            val user = hashMapOf(
                "first" to textfieldMonto.text.toString(),
                "last" to tv_agregarnombreproducto.text.toString(),
                "born" to 1815
            )

            // Add a new document with a generated ID
            try {
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this,"se agrego", Toast.LENGTH_SHORT).show();
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }


                // Create a new user with a first and last name

            }
            catch (e:Exception){
                Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show();

            }


        }


    }

}

@IgnoreExtraProperties
data class Post(
    var uid: String? = "",
    var author: String? = "",
    var title: String? = "",
    var body: String? = "",
    var starCount: Int = 0,
    var stars: MutableMap<String, Boolean> = HashMap()
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "author" to author,
            "title" to title,
            "body" to body,
            "starCount" to starCount,
            "stars" to stars
        )
    }
}