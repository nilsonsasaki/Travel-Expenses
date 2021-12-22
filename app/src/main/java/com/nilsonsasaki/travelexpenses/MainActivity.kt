package com.nilsonsasaki.travelexpenses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nilsonsasaki.travelexpenses.databinding.ActivityMainBinding
import com.nilsonsasaki.travelexpenses.viewmodels.TravelExpensesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = TravelExpensesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}