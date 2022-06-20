package com.example.bico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.example.bico.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       //setContentView(R.layout.activity_start)
        var binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.course.setOnClickListener{
            var intent = Intent(this, CourseActivity::class.java)
            startActivity(intent)
        }
        binding.location.setOnClickListener{
            var intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

        binding.diary.setOnClickListener{
            var intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
        }
    }
}