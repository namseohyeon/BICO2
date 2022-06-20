package com.example.bico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bico.databinding.ActivityDirayBinding
import com.example.bico.util.myCheckPermission

class DiaryActivity : AppCompatActivity() {
    lateinit var binding : ActivityDirayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_diray)
        binding = ActivityDirayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Bcourse.setOnClickListener{
            val intent = Intent(this, CourseActivity::class.java)
            startActivity(intent)
        }

        binding.Blocation.setOnClickListener{
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

        myCheckPermission(this)
        binding.addFab.setOnClickListener{
            if(loginApplication.checkAuth()){
                startActivity(Intent(this,AddActivity::class.java))
            }else{
                Toast.makeText(this,"인증을 진행해주세요",Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onStart() {
        super.onStart()
        makeRecyclerView()
    }
    private fun makeRecyclerView(){
        loginApplication.db.collection("news")
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<ItemData>()
                for(document in result){
                    val item = document.toObject(ItemData::class.java)
                    item.docId = document.id
                    itemList.add(item)
                }
                binding.diaryRecycleView.layoutManager = LinearLayoutManager(this)
                binding.diaryRecycleView.adapter = DiaryAdapter(this,itemList)
            }
            .addOnFailureListener{
                Toast.makeText(this,"서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }
}