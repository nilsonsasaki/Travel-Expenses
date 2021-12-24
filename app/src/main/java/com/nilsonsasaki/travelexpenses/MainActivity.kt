package com.nilsonsasaki.travelexpenses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nilsonsasaki.travelexpenses.databinding.ActivityMainBinding
import com.nilsonsasaki.travelexpenses.models.TravelValues
import com.nilsonsasaki.travelexpenses.viewmodels.TravelExpensesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = TravelExpensesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvResult.text = getString(R.string.br_price_value, "0.00")

        viewModel.travelValues.observe(this, { newValue ->
            binding.etDistance.setText(newValue.distance)
            binding.etPrice.setText(newValue.gasPrice)
            binding.etAutonomy.setText(newValue.autonomy)
            binding.tvResult.text = getString(R.string.br_price_value, newValue.travelCost)

        })

        viewModel.toastMessage.observe(this, { res ->
            if (res != null) {
                val message = getString(res)
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            }
        })

        binding.btCalculate.setOnClickListener {
            viewModel.calculateExpenses(
                TravelValues(
                    binding.etDistance.text.toString(),
                    binding.etPrice.text.toString(),
                    binding.etAutonomy.text.toString(),
                    "0.00"
                )
            )
        }
    }
}