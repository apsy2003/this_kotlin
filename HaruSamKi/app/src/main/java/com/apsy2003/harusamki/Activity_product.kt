package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityJoinBinding
import com.apsy2003.harusamki.databinding.ActivityProductBinding

class Activity_product : AppCompatActivity() {

    val binding by lazy { ActivityProductBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val intent = Intent(this, Activity_indexmain::class.java)
        binding.backBtn.setOnClickListener{ startActivity(intent)}
    }


}