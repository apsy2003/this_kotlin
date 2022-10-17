package com.apsy2003.widgetsratingbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.widgetsratingbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.ratingBar.setOnRatingBarChangeListener{ ratingBar, rating, fromUser ->
            binding.textView.text = "$rating"
        }
    }
}