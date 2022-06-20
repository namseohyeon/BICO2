package com.example.bico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bico.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_auth)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        changeVisivility(intent.getStringExtra("data").toString())

        binding.signup.setOnClickListener{
            val email = binding.bicoId.text.toString()
            val password = binding.bicoPw.text.toString()
            loginApplication.auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    binding.bicoId.text.clear()
                    binding.bicoPw.text.clear()
                    if(task.isSuccessful){
                        loginApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener{sendTask ->
                                Toast.makeText(baseContext,"회원가입성공! 메일을 확인해주세요",Toast.LENGTH_SHORT).show()
                                //val intent = Intent(this, AuthActivity::class.java)
                                //intent.putExtra("data","signup")
                                changeVisivility("login")
                                //startActivity(intent)
                            }
                    }else{
                        Toast.makeText(baseContext,"회원가입 실패",Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, StartActivity::class.java)
//                        startActivity(intent)
                    }

                }
        }
        binding.login.setOnClickListener{
            val email = binding.bicoId.text.toString()
            val password = binding.bicoPw.text.toString()
            loginApplication.auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) {task ->
                    binding.bicoId.text.clear()
                    binding.bicoPw.text.clear()
                    if(task.isSuccessful){
                        if(loginApplication.checkAuth()){
                            loginApplication.email = email
                            val intent = Intent(this, StartActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(baseContext,"이메일이 인증되지 않았습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(baseContext,"로그인 실패",Toast.LENGTH_SHORT).show()
                    }
                }

        }

    }
    fun changeVisivility(mode:String){
        if(mode.equals("login")){
            binding.run{
                bicoId.visibility = View.VISIBLE
                bicoPw.visibility = View.VISIBLE
                login.visibility =View.VISIBLE
                signup.visibility = View.GONE
            }
        } else if(mode.equals("signup")){
            binding.run {
                bicoId.visibility = View.VISIBLE
                bicoPw.visibility = View.VISIBLE
                login.visibility = View.GONE
                signup.visibility = View.VISIBLE
            }
        }
    }
}