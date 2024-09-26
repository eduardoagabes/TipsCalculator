package com.eduardoagabes.tipscalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtFactura = findViewById<TextInputEditText>(R.id.edt_factura_total)
        val edtPersonas = findViewById<TextInputEditText>(R.id.edt_personas)

        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val btnLimpiar = findViewById<Button>(R.id.btn_limpiar)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)


        val limpiarCampo1 = findViewById<EditText>(R.id.edt_factura_total)
        val limpiarCampo2 = findViewById<EditText>(R.id.edt_personas)

        btnCalcular.setOnClickListener {
            val facturaStr: String = edtFactura.text.toString()
            val personasStr: String = edtPersonas.text.toString()

            if (facturaStr == "" || personasStr == "") {
                Snackbar
                    .make(
                        edtFactura,
                        "Rellena todos los campos",
                        Snackbar.LENGTH_LONG
                    )
                    .show()
            } else {

                val factura = facturaStr.toFloat()
                val personas = personasStr.toInt()



                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
            }
        }

        btnLimpiar.setOnClickListener {
            limpiarCampo1.text.clear()
            limpiarCampo2.text.clear()
        }
    }
}