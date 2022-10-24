package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityIndexmainBinding
import com.apsy2003.harusamki.databinding.ActivityLoginBinding

class Activity_indexmain : AppCompatActivity() {

    val binding by lazy { ActivityIndexmainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent1 = Intent(this, Activity_product::class.java)
        binding.moreBtn.setOnClickListener{ startActivity(intent1)}
    }
}