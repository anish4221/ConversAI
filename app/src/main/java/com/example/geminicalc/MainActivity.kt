package com.example.geminicalc

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.view.ActionMode.Callback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geminicalc.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
        binding.addButton.setOnClickListener {
            val inputGemini = binding.inputEdit.text.toString()

            // val retrofit = RetrofitClient.getInstance()
            val apiInterface = RetrofitClient.getInstance().create(GeminiInterface::class.java)

            sendtogemini(inputGemini)


        }

    }
    fun sendtogemini(input: String){
        val apiInterface = RetrofitClient.getInstance().create(GeminiInterface::class.java)
        apiInterface.getResponse(input).enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                binding.outputText.text = response.body().toString()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                binding.outputText.text = t.toString()
            }

        })
    }
}













