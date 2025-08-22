package com.example.appformulario

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        val nombreProducto = findViewById<TextInputEditText>(R.id.txtNombreProducto)
        val precio = findViewById<TextInputEditText>(R.id.txtPrecio)
        val spinnerCategoria = findViewById<Spinner>(R.id.spinnerCategoria)
        val chkDisponible = findViewById<CheckBox>(R.id.chkDisponible)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarProducto)

        btnGuardar.setOnClickListener {
            val nombreTxt = nombreProducto.text.toString().trim()
            val precioTxt = precio.text.toString().trim()
            val categoria = spinnerCategoria.selectedItem.toString()
            val disponible = if (chkDisponible.isChecked) "Sí" else "No"

            when {
                nombreTxt.isEmpty() -> {
                    Toast.makeText(this, "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show()
                }
                precioTxt.isEmpty() -> {
                    Toast.makeText(this, "Ingrese el precio", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(
                        this,
                        "Producto: $nombreTxt\nPrecio: $precioTxt\nCategoría: $categoria\nDisponible: $disponible",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
