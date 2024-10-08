package com.eduardoagabes.tipscalculator


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eduardoagabes.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val numPeopleTemp = binding.tieNumberOfPeople.text
            val percentageTemp = binding.tiePercentage.text

            if (totalTableTemp?.isEmpty() == true || numPeopleTemp?.isEmpty() == true || percentageTemp?.isEmpty() == true) {
                Snackbar.make(binding.tieTotal, "Rellene todos los campos", Snackbar.LENGTH_LONG)
                    .show()

            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Int = percentageTemp.toString().toInt()

                val totalTemp = totalTable / nPeople
                val tips = (totalTemp * percentage) / 100
                val totalWhitTips = totalTemp + tips

                val intent = Intent(this, ResultActivity::class.java)
                intent.apply {
                    putExtra("totalTable", totalTable)
                    putExtra("numPeople", nPeople)
                    putExtra("percentage", percentage)
                    putExtra("totalAmount", totalWhitTips)
                    putExtra("tips", tips)

                }
                clean()
                startActivity(intent)
            }
        }

        binding.btnClean.setOnClickListener {
            clean()
        }

    }

    private fun clean() {
        binding.tieTotal.setText("")
        binding.tieNumberOfPeople.setText("")
        binding.tiePercentage.setText("")
    }
}

