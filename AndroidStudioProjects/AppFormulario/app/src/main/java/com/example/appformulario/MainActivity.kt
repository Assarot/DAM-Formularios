package com.example.appformulario

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val nombre = findViewById<TextInputEditText>(R.id.txtNombre)
        val correo = findViewById<TextInputEditText>(R.id.txtCorreo)
        val contrasenia = findViewById<TextInputEditText>(R.id.txtContrasenia)
        val generoMasculino = findViewById<RadioButton>(R.id.rbMasculino)
        val generoFemenino = findViewById<RadioButton>(R.id.rbFemenino)
        val chkTerminos = findViewById<CheckBox>(R.id.chkTerminos)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnIrProductos = findViewById<Button>(R.id.btnIrProductos)

        btnIrProductos.setOnClickListener {
            val intent = Intent(this, ProductoActivity::class.java)
            startActivity(intent)
        }

        btnRegistrar.setOnClickListener {
            val nombreTxt = nombre.text.toString().trim()
            val correoTxt = correo.text.toString().trim()
            val contraseniaTxt = contrasenia.text.toString().trim()
            val generoSeleccionado = generoMasculino.isChecked || generoFemenino.isChecked

            when {
                nombreTxt.isEmpty() -> {
                    Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show()
                }

                correoTxt.isEmpty() -> {
                    Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show()
                }

                contraseniaTxt.isEmpty() -> {
                    Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show()
                }

                !generoSeleccionado -> {
                    Toast.makeText(this, "Seleccione un género", Toast.LENGTH_SHORT).show()
                }

                !chkTerminos.isChecked -> {
                    Toast.makeText(
                        this,
                        "Debe aceptar los términos y condiciones",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val genero = if (generoMasculino.isChecked) "Masculino" else "Femenino"
                    Toast.makeText(
                        this,
                        "Registro: $nombreTxt, $correoTxt, $genero",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}