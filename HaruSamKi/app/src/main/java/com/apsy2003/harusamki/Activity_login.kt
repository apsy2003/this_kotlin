package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityLoginBinding


class Activity_login : AppCompatActivity() {
    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent1 = Intent(this, Activity_join::class.java)
        binding.joinbtn.setOnClickListener{ startActivity(intent1)}

        val intent2 = Intent(this, Activity_indexmain::class.java)
        binding.loginbtn.setOnClickListener{ startActivity(intent2)}
    }
}
