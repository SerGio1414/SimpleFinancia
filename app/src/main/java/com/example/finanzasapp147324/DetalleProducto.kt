package com.example.finanzasapp147324

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class DetalleProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        val tv_nombre: TextView = findViewById(R.id.nombre_detalle)
        val tv_desc: TextView = findViewById(R.id.desc_detalle)
        val img_desc: ImageView = findViewById(R.id.img_detalle)

        val bundle= intent.extras

        if (bundle!=null){
            var nombre: String? = bundle.getString("nombre")
            var img = bundle.getInt("img")
            var desc: String?  = bundle.getString("desc")

            tv_nombre.setText(nombre)
            tv_desc.setText(desc)
            img_desc.setImageResource(img)
        }
    }
}