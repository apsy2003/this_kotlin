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

        val contentreview = Intent(this, Activity_review::class.java)
        binding.content7.setOnClickListener{ startActivity(contentreview)}

        val intent1 = Intent(this, Activity_product::class.java)
        binding.moreBtn.setOnClickListener{ startActivity(intent1)}

        val intent2 = Intent(this, Activity_Community::class.java)
        binding.fmenu2.setOnClickListener{ startActivity(intent2)}



    }
}