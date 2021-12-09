package com.example.finanzasapp147324

import android.content.ContentValues
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
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
        val textViewgastoPorMes:TextView = vista.findViewById(R.id.tv_gastoPorMes)
        val textViewid:TextView = vista.findViewById(R.id.tv_idProducto)

        var producto: Producto = productos[p0]



        textViewNombre.setText(producto.nombre)
        textViewDescripcion.setText(producto.descripcion)
        img.setImageResource(producto.imagen)
        textViewgastoPorMes.setText(producto.gastoPorMes)
        textViewid.setText(producto.id)


        vista.setOnClickListener {
            val intent: Intent = Intent(context, DetalleProducto::class.java)
            intent.putExtra("id", producto.id)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("desc", producto.descripcion)
            intent.putExtra("img", producto.imagen)
            intent.putExtra("gastoPorMes", producto.gastoPorMes)


            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${producto.id}")
            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${producto.nombre}")
            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${producto.descripcion}")
            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${producto.gastoPorMes}")
        //    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${producto.id}")

            Toast.makeText(context,producto.id,Toast.LENGTH_SHORT).show();

            context.startActivity(intent)
        }



        vista.findViewById<TextView>(R.id.tv_idProducto).text = producto.id.toString()


        vista.findViewById<ImageView>(R.id.btnBorrarProducto).setOnClickListener{
            eliminar(producto.id.toString())
            Toast.makeText(context,"Producto eliminado",Toast.LENGTH_SHORT).show();

            // Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${context}")

           // this.notifyDataSetChanged()

        }


        /////////////MODIFICAR Producto
        vista.findViewById<ImageView>(R.id.btnModificarProducto).setOnClickListener{

            /*
            val intent: Intent = Intent(context, ModificarProductoActivity::class.java)
            intent.putExtra("id", producto.id)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("desc", producto.descripcion)
            intent.putExtra("img", producto.imagen)
            intent.putExtra("gastoPorMes", producto.gastoPorMes)
            */

            val intent2 = Intent(context, ModificarProductoActivity::class.java)
            // start your next activity
            context.startActivity(intent2)

            Toast.makeText(context,"Test boton",Toast.LENGTH_SHORT).show();

           // context.startActivity(intent)
        }


        return vista

    }

    private fun eliminar(id: String){



        val db = Firebase.firestore

        db.collection("productos").document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }

        /*
        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                run loop@{
                    for (document in result) {
                        println("Day_doc_id : " + document.reference.parent.parent?.id)
                        val idEliminarProducto = document.get(id);

                        db.collection("productos").document(idEliminarProducto.toString())
                            .delete()
                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }


                        Toast.makeText(context, idEliminarProducto.toString(), Toast.LENGTH_SHORT).show()

                        if (idEliminarProducto == id) return@loop // non-local return from the lambda passed to run
                        print(idEliminarProducto)
                    }
                }

            }

        Toast.makeText(context, "Prueba loop", Toast.LENGTH_SHORT).show()
     */
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



