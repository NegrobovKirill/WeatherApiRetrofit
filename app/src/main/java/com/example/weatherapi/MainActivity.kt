package com.example.weatherapi

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.weatherapi.databinding.ActivityMainBinding
import com.example.weatherapi.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val model: ViewModelClass by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextText.setText(model.getCurrentCity())
        binding.temp.text = model.getCurrentTemp()
        binding.date.text = model.getCurrentDate()

        binding.bRequest.setOnClickListener {
            if (binding.editTextText.text.toString() != "") {
                model.setCity(binding.editTextText.text.toString())
            }
            CoroutineScope(Dispatchers.IO).launch {
                model.runRequest()
                runOnUiThread {
                    binding.editTextText.setText(model.getCurrentCity())
                    binding.temp.text = model.getCurrentTemp()
                    binding.date.text = model.getCurrentDate()
                }
            }



        }
    }

}

