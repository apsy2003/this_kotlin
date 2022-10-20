package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityMainBinding
import com.apsy2003.harusamki.databinding.ActivityOnboard2Binding

class Activity_onboard2 : AppCompatActivity() {
    val binding by lazy { ActivityOnboard2Binding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, Activity_onboard3::class.java)
        binding.nextBtn.setOnClickListener{ startActivity(intent)}
    }
}