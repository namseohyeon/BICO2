package com.example.bico

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bico.databinding.ActivityAddBinding
import com.example.bico.util.dateToString
import com.google.firebase.storage.StorageReference
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity() {
//    lateinit var  requestCameraFileLauncher: ActivityResultLauncher<Intent>
    lateinit var photoURI: Uri
    lateinit var filePath: String
    lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.diary_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode === android.app.Activity.RESULT_OK){
            Log.d("mobileApp","plz")
            Glide
                .with(applicationContext)
                .load(it.data?.data)
                .apply(RequestOptions().override(250,200))
                .into(binding.addImgView)

            val cursor = contentResolver.query(it.data?.data as Uri, arrayOf<String>(MediaStore.Images.Media.DATA),null,null,null);
            cursor?.moveToFirst().let{
                filePath=cursor?.getString(0) as String
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.gallery){
            Log.d("mobileApp","plz image")
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            requestLauncher.launch(intent)
        }
        else if(item.itemId === R.id.save){
            if(binding.addImgView.drawable != null && binding.addEditView.text.isNotEmpty()){
                saveStore()
            }else{
                Toast.makeText(this,"데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveStore(){
        val data = mapOf(
            "email" to loginApplication.email,
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )

        loginApplication.db.collection("news")
            .add(data)
            .addOnSuccessListener {
                uploadImage(it.id)
            }
            .addOnFailureListener {
                Log.d("mobileApp", "data save error")
            }
    }

    private fun uploadImage(docId:String){
        val storage = loginApplication.storage
        val storageRef : StorageReference =  storage.reference
        val imageRef = storageRef.child("images/${docId}.jpg")

        val file = Uri.fromFile(File(filePath))
        imageRef.putFile(file)
            .addOnSuccessListener {
                Toast.makeText(this,"save...ok.", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Log.d("mobileApp", "File save error")
            }
    }
}