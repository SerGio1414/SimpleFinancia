package com.example.finanzasapp147324

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.FirebaseFirestore
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
        }

        btn_guardarProducto.setOnClickListener {

            val productoNombre = tv_agregarProductoNombre.text.toString()
            val productoDescripcion = tv_agregarProductoDescripcion.text.toString()
            val productoPrecio = tv_agregarProductoGastoMen.text.toString()
            val id = getMaxId(db).toString();

            val product = hashMapOf(
                "id" to id,
                "nombre" to productoNombre,
                "descripcion" to productoDescripcion,
                "gastoPorMes"   to productoPrecio
            )


            try {
                db.collection("productos")
                    .add(product)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this,"Producto agregado", Toast.LENGTH_SHORT).show();
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e);
                    }
                finish();
            }
            catch (e:Exception){
                Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show();
            }

        }

    }

    fun getMaxId(db: FirebaseFirestore): Int{
        var max = 0;

        try {
            val collectionReference = db.collectionGroup("productos").get();

            while(!collectionReference.isComplete){

            }

            for(item in collectionReference.result){

                val sid = item.get("id").toString();
                val id = sid.toInt();
                if(id > max){
                    max = id;
                }
            }
        }
        catch (ex: Exception){
            Toast.makeText(this,ex.message.toString(),Toast.LENGTH_LONG).show();
        }

        return max+1;
    }

}
