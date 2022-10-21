package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityLoginBinding
import com.apsy2003.harusamki.databinding.ActivityOnboard2Binding
import com.apsy2003.harusamki.databinding.ActivityOnboard3Binding

class Activity_onboard3 : AppCompatActivity() {

    val binding by lazy { ActivityOnboard3Binding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, Activity_login::class.java)
        binding.startBtn3.setOnClickListener{ startActivity(intent)}
    }
}