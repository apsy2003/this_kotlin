package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityJoinBinding


class Activity_join : AppCompatActivity() {
    val binding by lazy { ActivityJoinBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, Activity_login::class.java)
        binding.back.setOnClickListener{ startActivity(intent)}
    }
}