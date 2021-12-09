package com.example.finanzasapp147324

import android.content.ContentValues
import android.content.ContentValues.TAG
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

        btn_cancelaragregarproducto.setOnClickListener {
            finish();
            //val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            //startActivity(intent)
        }

        btn_guardarProducto.setOnClickListener {
                val productoNombre = tv_agregarProductoNombre.text.toString()
                val productoDescripcion = tv_agregarProductoDescripcion.text.toString()
                val productoPrecio = tv_agregarProductoGastoMen.text.toString()

            // Create a new user with a first and last name
            val product = hashMapOf(
                "id" to "1",
                "nombre" to tv_agregarProductoNombre.text.toString(),
                "descripcion" to tv_agregarProductoDescripcion.text.toString(),
                "gastoPorMes"   to tv_agregarProductoGastoMen.text.toString()
            )

            // Add a new document with a generated ID
            try {


                db.collection("productos")
                    .add(product)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this,"Producto agregado", Toast.LENGTH_SHORT).show();
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                        val productoAuxMod = db.collection("productos").document(documentReference.id)

                        productoAuxMod
                            .update("id",documentReference.id)
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                        finish();
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e);
                        finish();
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