package com.example.finanzasapp147324

import android.content.Intent
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


        val bundle = intent.extras

        if (bundle != null) {
            val name = bundle.getString("name")
            val email = bundle.getString("email")
            // tv_nombre.setText(name)
            tv_nombre.setText(name)
        }

        agregarPeliculas();
        val adaptador: AdaptadorProductos= AdaptadorProductos(this,productos)



        val listView: ListView = findViewById(R.id.listView)

        listView.adapter= adaptador



        btn_Anadir.setOnClickListener {
            val intent = Intent(this, AgregarProductoActivity::class.java)
            // start your next activity
            startActivity(intent)

        }

        btn_Salir.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            // start your next activity
            finish()
        }

        btn_Grafica.setOnClickListener {
            val intent = Intent(this, GraficaActivity::class.java)
            // start your next activity
            startActivity(intent)

        }




        btn_Salir.setOnClickListener {
            finish()
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




    override fun onBackPressed() {

    }



}

