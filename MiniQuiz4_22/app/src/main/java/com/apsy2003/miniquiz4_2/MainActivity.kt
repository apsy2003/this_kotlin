package com.apsy2003.miniquiz4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import com.apsy2003.miniquiz4_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var txtView : textView
    lateinit var rdGroup : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_main)

        txtView = findViewById(R.id.txtView)
        rdGroup = findViewById(R.id.rdGroup)
0
        rdGroup.setOnCheckedChangeListener{

        }
    }
}