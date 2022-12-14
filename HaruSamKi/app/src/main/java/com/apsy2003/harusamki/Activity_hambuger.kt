package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityHambugerBinding
import com.apsy2003.harusamki.databinding.ActivityProductBinding

class Activity_hambuger : AppCompatActivity() {
    val binding by lazy{ ActivityHambugerBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //되돌아가기 버튼
        val Back = Intent(this, Activity_indexmain::class.java)
        binding.innericon.setOnClickListener{ startActivity(Back)}

        //로고 박스 링크 연결
        val Logo = Intent(this, Activity_indexmain::class.java)
        binding.hlogo.setOnClickListener{ startActivity(Logo)}

        //마이페이지 이동 링크 연결
        val Mypage = Intent(this, Activity_mypage::class.java)
        binding.mypageBtn.setOnClickListener{ startActivity(Mypage)}

        //타이머 이동 링크 연결
        val Timer = Intent(this, Activity_Timer::class.java)
        binding.timerBtn.setOnClickListener{ startActivity(Timer)}

        //나의 일정 이동 링크 연결
        val ToDo = Intent(this, Activity_ToDoMain::class.java)
        binding.group7.setOnClickListener{ startActivity(ToDo)}

        //1:1 고객상담
        val Customer = Intent(this, CustomerChatList::class.java)
        binding.group2.setOnClickListener{startActivity(Customer)}


    }
}