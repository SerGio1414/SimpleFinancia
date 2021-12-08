package com.example.finanzasapp147324

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.lang.Exception


class AdaptadorProductos: BaseAdapter {
    lateinit var context: Context
    var productos: ArrayList<Producto> = ArrayList();

    constructor(context: Context, productos: ArrayList<Producto>) {
        this.context = context;
        this.productos = productos;
    }

    override fun getCount(): Int {
        return productos.size
    }

    override fun getItem(p0: Int): Any {
        return productos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return productos[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflador = LayoutInflater.from(context)
        val vista = inflador.inflate(R.layout.producto_view, null)
        val textViewNombre: TextView = vista.findViewById(R.id.textViewNombre)
        val textViewDescripcion: TextView = vista.findViewById(R.id.tv_desc)
        val img: ImageView = vista.findViewById(R.id.img_peli)
        var textViewDgastoPorMes:TextView = vista.findViewById(R.id.tv_gastoPorMes)
        var producto: Producto = productos[p0]



        textViewNombre.setText(producto.nombre)
        textViewDescripcion.setText(producto.descripcion)
        img.setImageResource(producto.imagen)
        textViewDgastoPorMes.setText(producto.gastoPorMes)

        vista.setOnClickListener {
            val intent: Intent = Intent(context, DetalleProducto::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("desc", producto.descripcion)
            intent.putExtra("img", producto.imagen)
            intent.putExtra("gastoPorMes", producto.gastoPorMes)

            context.startActivity(intent)
        }

        vista.findViewById<ImageView>(R.id.btnBorrar).setOnClickListener{
             eliminar(producto.nombre)
            Toast.makeText(context, producto.nombre, Toast.LENGTH_SHORT).show()

            this.notifyDataSetChanged()
        }


        return vista

    }

    private fun eliminar(nombre: String){



        val db = Firebase.firestore


        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                run loop@{
                    for (document in result) {
                        println("Day_doc_id : " + document.reference.parent.parent?.id)
                        val first_name = document.get(nombre);

                        db.collection("productos").document(first_name.toString())
                            .delete()
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }


                        Toast.makeText(context, first_name.toString(), Toast.LENGTH_SHORT).show()

                        if (first_name == nombre) return@loop // non-local return from the lambda passed to run
                        print(first_name)
                    }
                }

            }

        Toast.makeText(context, nombre, Toast.LENGTH_SHORT).show()

       // Toast.makeText(context, "Se elimino el archivo", Toast.LENGTH_SHORT).show()


    /*
        if (titulo==""){
            Toast.makeText(context, "Error: título vacío", Toast.LENGTH_SHORT).show()

        }else{
            try {
                //elimina el archivo con el titulo seleccionado
             // val archivo = File(ubicacion(),titulo+".txt")
               // archivo.delete()

                Toast.makeText(context, "Se elimino el archivo", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(context, "Error al eliminar el archivo", Toast.LENGTH_SHORT).show()
            }
        }

        */
    }
}



