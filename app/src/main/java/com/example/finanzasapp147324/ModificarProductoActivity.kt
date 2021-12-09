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

      //  tv_agregarProductoNombre.setText("Hola")



        tv_agregarProductoNombre.setText(getIntent().getSerializableExtra("nombre").toString())
        tv_agregarProductoDescripcion.setText(getIntent().getSerializableExtra("id").toString())
        tv_agregarProductoGastoMen.setText(getIntent().getSerializableExtra("id").toString())







    btn_cancelaragregarproducto.setOnClickListener {
            finish();
            //val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            //startActivity(intent)
        }
    }
    }
