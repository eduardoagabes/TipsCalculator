package com.eduardoagabes.tipscalculator


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eduardoagabes.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var percentage: Int = 0

        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 10
            }
        }

        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 15
            }
        }

        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 20
            }
        }

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.num_people,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNumberOfPeople.adapter = adapter

        var numOfPeopleSelected = 0
        binding.spinnerNumberOfPeople.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    numOfPeopleSelected = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text

            if (totalTableTemp?.isEmpty() == true) {
                Snackbar.make(binding.tieTotal, "Rellene todos los campos", Snackbar.LENGTH_LONG)
                    .show()

            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numOfPeopleSelected

                val totalTemp = totalTable / nPeople
                val tips = (totalTemp * percentage) / 100
                val totalWhitTips = totalTemp + tips

                val formattedResult = NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(totalWhitTips)
                val formattedFactura = NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(totalTable)
                val formattedTips= NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(tips)

                binding.tvResult.text = formattedResult
                binding.tvFactura.text = formattedFactura
                binding.tvTip.text = formattedTips

            }

            binding.btnClean.setOnClickListener {
                binding.tvResult.text = ""
                binding.tvFactura.text = ""
                binding.tvTip.text = ""
                binding.tieTotal.setText("")
                binding.rgPercentage.clearCheck()
                binding.spinnerNumberOfPeople.setSelection(0)
            }
        }
    }
}
