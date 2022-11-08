package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityMypageBinding
import com.apsy2003.harusamki.databinding.ActivityReviewBinding

class Activity_mypage : AppCompatActivity() {
    val binding by lazy{ ActivityMypageBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //되돌아가기 버튼
        val Back = Intent(this, Activity_indexmain::class.java)
        binding.iconBox.setOnClickListener{ startActivity(Back)}

        //햄버거 메뉴 링크 연결
        val hamMenu = Intent(this, Activity_hambuger::class.java)
        binding.head4.setOnClickListener{ startActivity(hamMenu)}

        //커뮤니티 링크 연결
        val ComList = Intent(this, Activity_Community::class.java)
        binding.communityList.setOnClickListener{ startActivity(ComList)}

        //1:1 고객상담
        val Customer = Intent(this, CustomerChatList::class.java)
        binding.settingm4.setOnClickListener{startActivity(Customer)}

        //하단 footer메뉴 링크 연결
        val Footmenu1 = Intent(this, Activity_review::class.java)
        binding.fmenu1.setOnClickListener{ startActivity(Footmenu1)}

        val Footmenu2 = Intent(this, Activity_Community::class.java)
        binding.fmenu2.setOnClickListener{ startActivity(Footmenu2)}

        val Footmenu3 = Intent(this, Activity_indexmain::class.java)
        binding.fmenu3.setOnClickListener{ startActivity(Footmenu3)}

        val Footmenu4 = Intent(this, Activity_mypage::class.java)
        binding.fmenu4.setOnClickListener{ startActivity(Footmenu4)}

        val Footmenu5 = Intent(this, Activity_setting::class.java)
        binding.fmenu5.setOnClickListener{ startActivity(Footmenu5)}
    }
}