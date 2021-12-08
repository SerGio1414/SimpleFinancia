package com.example.finanzasapp147324

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.producto_view.view.*


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
        textViewDescripcion.setText(producto.desc)
        img.setImageResource(producto.imagen)
        textViewDgastoPorMes.setText(producto.gastoPorMes)

        vista.setOnClickListener {
            val intent: Intent = Intent(context, DetalleProducto::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("desc", producto.desc)
            intent.putExtra("img", producto.imagen)
            intent.putExtra("gastoPorMes", producto.gastoPorMes)

            context.startActivity(intent)
        }

        return vista

    }
}



