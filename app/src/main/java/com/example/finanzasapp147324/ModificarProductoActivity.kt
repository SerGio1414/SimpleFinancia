package com.example.finanzasapp147324

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_agregar_producto.*

class ModificarProductoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)
        val db = Firebase.firestore
        val objectId = getIntent().getSerializableExtra("id").toString();
        tv_agregarProductoNombre.setText(getIntent().getSerializableExtra("nombre").toString());
        tv_agregarProductoDescripcion.setText(getIntent().getSerializableExtra("desc").toString());
        tv_agregarProductoGastoMen.setText(getIntent().getSerializableExtra("gastoPorMes").toString());

        btn_guardarProducto.setOnClickListener {
            try {

                val productoNombre = tv_agregarProductoNombre.text.toString()
                val productoDescripcion = tv_agregarProductoDescripcion.text.toString()
                val productoPrecio = tv_agregarProductoGastoMen.text.toString()
                val id = objectId;

                var attributes = HashMap<String,String>();
                attributes.put("id",id);
                attributes.put("nombre",productoNombre);
                attributes.put("descripcion",productoDescripcion);
                attributes.put("gastoPorMes",productoPrecio);

                var result = db.collection("productos").whereEqualTo("id",objectId).get();
                while (!result.isComplete){ }
                val DocumentID = result.result.documents.first().id;

                db.collection("productos").document(DocumentID)
                    .update(attributes as Map<String, Any>)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this,"Producto agregado", Toast.LENGTH_SHORT).show();
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${DocumentID}")
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




    btn_cancelaragregarproducto.setOnClickListener {
            finish();
        }
    }
    }
