package com.apsy2003.widgetsedittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.editText.addTextChangedListener{
            if(it.toString().length >=8){
                Log.d("EditText", "현재 입력된 값=${it.toString()}")
            }
        }
    }
}