package com.example.finanzasapp147324

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var productos:ArrayList<Producto> = ArrayList();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        agregarPeliculas();
        val adaptador: AdaptadorProductos= AdaptadorProductos(this,productos)

        val listView: ListView = findViewById(R.id.listView)

        listView.adapter= adaptador


        btn_Añadir.setOnClickListener {


        }

        btn_Salir.setOnClickListener{

        }

        btn_Grafica.setOnClickListener {

        }

    }



    fun agregarPeliculas(){
        productos.add(Producto(1,"Agua","Agua 1",R.drawable.c));
        productos.add(Producto(2,"Croquetas","Croquetas 2",R.drawable.d));
        productos.add(Producto(3,"Electricidad","Electricidad 1",R.drawable.a));
        productos.add(Producto(4,"TEST","TEST 3",R.drawable.img));
        productos.add(Producto(5,"Agua","Agua 2",R.drawable.c));
        productos.add(Producto(6,"Agua","Agua 3",R.drawable.c));


    }



}

