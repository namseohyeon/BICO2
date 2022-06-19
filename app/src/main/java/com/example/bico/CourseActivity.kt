package com.example.bico

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.preference.PreferenceManager
import com.example.bico.databinding.ActivityCourseBinding
import com.example.bico.databinding.ActivityStartBinding

class CourseActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityCourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_course)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val bgColor = sharedPreferences.getString("color","")
        binding.activityContent.setBackgroundColor(Color.parseColor(bgColor))


        val Realfragment = realFragment()
        val bundle = Bundle()
        bundle.putString("returnType", "json")
        Realfragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, Realfragment)
            .commit()


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.color, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.setting) {
            val intent = Intent(this, ColorSettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val bgColor = sharedPreferences.getString("color","")
        binding.activityContent.setBackgroundColor(Color.parseColor(bgColor))
    }


}


