package com.example.finanzasapp147324

import android.app.Activity
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception


class AdaptadorProductos: BaseAdapter {
    lateinit var context: Context
    var productos: ArrayList<Producto> = ArrayList();
    val db = Firebase.firestore

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

            Toast.makeText(context,producto.id,Toast.LENGTH_SHORT).show();

            context.startActivity(intent)
        }



        vista.findViewById<TextView>(R.id.tv_idProducto).text = producto.id.toString()


        vista.findViewById<ImageView>(R.id.btnBorrarProducto).setOnClickListener{
            eliminar(producto.id.toString(),db);
            Toast.makeText(context,"Producto eliminado",Toast.LENGTH_SHORT).show();

        }


        /////////////MODIFICAR Producto
        vista.findViewById<ImageView>(R.id.btnModificarProducto).setOnClickListener{


            val intent: Intent = Intent(context, ModificarProductoActivity::class.java)
            intent.putExtra("id", producto.id)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("desc", producto.descripcion)
            intent.putExtra("img", producto.imagen)
            intent.putExtra("gastoPorMes", producto.gastoPorMes)

            context.startActivity(intent)

            Toast.makeText(context,"Test boton",Toast.LENGTH_SHORT).show();

         }


        return vista

    }

    private fun eliminar(id: String, db: FirebaseFirestore){
        try {

            var result = db.collection("productos").whereEqualTo("id",id).get();

            while (!result.isComplete){

            }

            val DocumentID = result.result.documents.first().id;


            db.collection("productos").document(DocumentID)
                .delete()
                .addOnSuccessListener { result ->
                    (context as MainActivity).recreate();
                    /*val intent = Intent(context, MainActivity::class.java)

                    context.startActivity(intent);*/
                    Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
        }catch (ex: Exception){
            Toast.makeText(context,ex.message.toString(),Toast.LENGTH_LONG).show();
        }

    }
}



