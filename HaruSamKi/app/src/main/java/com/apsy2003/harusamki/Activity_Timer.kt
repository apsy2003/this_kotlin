package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.apsy2003.harusamki.databinding.ActivityTimerBinding
import kotlin.concurrent.thread

class Activity_Timer : AppCompatActivity() {
    val binding by lazy {ActivityTimerBinding.inflate(layoutInflater)}
    var total = 0
    var started = false

    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message){
            val minute = String.format("%02d", total/60)
            val second = String.format("%02d", total%60)
            binding.textTimer.text = "$minute:$second"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, Activity_hambuger::class.java)
        binding.Timerback.setOnClickListener{ startActivity(intent)}

        binding.buttonStart.setOnClickListener{
            started = true
            thread(start=true){
                while (started){
                    Thread.sleep(1000)
                    if (started){
                        total = total +1
                        handler?.sendEmptyMessage(0)
                    }
                }
            }
        }

        binding.buttonStop.setOnClickListener{
            if (started){
                started = false
                total = 0
                binding.textTimer.text = "00:00"
            }
        }
    }

}

