package com.eduardoagabes.tipscalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eduardoagabes.tipscalculator.databinding.ActivityResultBinding
import java.text.NumberFormat
import java.util.Locale

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalTable = intent.getFloatExtra("totalTable", 0.0f)
        val nPeople = intent.getIntExtra("numPeople", 0)
        val percentage = intent.getIntExtra("percentage", 0)
        val totalWhitTips = intent.getFloatExtra("totalAmount", 0.0f)

        val formattedResult =
            NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(totalWhitTips)

        binding.tvPercentage.text = percentage.toString() + "%"
        binding.tvTotalAmount.text = formattedResult.toString()
        binding.tvTotalTable.text = totalTable.toString()
        binding.tvNumPeopleTable.text = nPeople.toString()

        binding.btnRefresh.setOnClickListener {
            finish()
        }

    }
}