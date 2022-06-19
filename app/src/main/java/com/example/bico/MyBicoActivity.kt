package com.example.bico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bico.databinding.ActivityMainBinding
import com.example.bico.databinding.ActivityMyBicoBinding

class MyBicoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyBicoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_my_bico)
        binding = ActivityMyBicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        if(loginApplication.email != null){
            binding.name.text = "${loginApplication.email} 님 안녕하세요"
            binding.name.textSize = 24F

        }else if(loginApplication.checkAuth()){
            binding.name.text = "${loginApplication.email} 님 안녕하세요"
            binding.name.textSize = 24F
        }
    }
}