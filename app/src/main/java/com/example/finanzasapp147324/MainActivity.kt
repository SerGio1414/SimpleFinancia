package com.example.finanzasapp147324

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
            tv_nombre.setText(name)
        }


        val db = Firebase.firestore


// Add a new document with a generated ID
        /*db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this,"se agrego",Toast.LENGTH_SHORT).show();
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"no se agrego",Toast.LENGTH_SHORT).show();
                Log.w(ContentValues.TAG, "Error adding document", e)
            }*/

            val collectionReference = db.collectionGroup("productos").get();


            db.collection("productos")
            .get()
            .addOnSuccessListener { result ->

                for(document in result) {
                    println("Day_doc_id : " + document.reference.parent.parent?.id)
                    val first_name = document.get("nombre");
                }

            }

            while(!collectionReference.isComplete){

            }

            var count: Int = 1;
            for(item in collectionReference.result){

                val first_name = item.get("nombre");
                val descrip = item.get("descripcion");
                val gasto = item.get("gastoPorMes");
                val id = item.get("id");
                //Toast.makeText(this,first_name.toString(),Toast.LENGTH_LONG).show();
                val prod = Producto(id.toString(),first_name.toString(),descrip.toString(),R.drawable.c,gasto.toString());
                productos.add(prod);
                count = count + 1;
            }





/*
        val db = FirebaseFirestore.getInstance()
        db.collection("productos").document("hGeve7mBlJkJBkORs0g7").get().addOnSuccessListener { document ->
            document?.let {
                Log.d("Firebase","DocumentSnapshot data: ${document.data}")
            }
        }
*/


     ///////////////////////////Establecer usuarios en el list view como lista

        //agregarPeliculas();
        val adaptador: AdaptadorProductos= AdaptadorProductos(this,productos)



        val listView: ListView = findViewById(R.id.listView)

        listView.adapter= adaptador



        btn_Anadir.setOnClickListener {
            val intent = Intent(this, AgregarProductoActivity::class.java)
            // start your next activity
            startActivity(intent)

        }



        btn_Grafica.setOnClickListener {
            val intent = Intent(this, GraficaActivity::class.java)
            // start your next activity
            startActivity(intent)

        }




        val rootRef = FirebaseDatabase.getInstance().reference
        val locationRef = rootRef.child("users")
        locationRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (ds in task.result.children) {
                    val name = ds.child("name").getValue(String::class.java)
                    Log.d(TAG, name!!)
                }
            } else {
                Log.d(TAG, task.exception!!.message!!) //Don't ignore potential errors!
            }

        }





    btn_Salir.setOnClickListener {
            finish()
        }
    }


    override fun onRestart() {
    // TODO Auto-generated method stub
        super.onRestart()
        val db = Firebase.firestore;
        productos.clear();

        val collectionReference = db.collectionGroup("productos").get();



        while(!collectionReference.isComplete){

        }


        for(item in collectionReference.result){

            val first_name = item.get("nombre");
            val descrip = item.get("descripcion");
            val gasto = item.get("gastoPorMes");
            val id = item.get("id");
            val prod = Producto(id.toString(),first_name.toString(),descrip.toString(),R.drawable.c,gasto.toString());
            productos.add(prod);
        }

        val adaptador: AdaptadorProductos= AdaptadorProductos(this,productos)

        val listView: ListView = findViewById(R.id.listView)

        listView.adapter= adaptador

    }



}


