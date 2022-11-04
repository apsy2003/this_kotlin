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

        //로고 박스 링크 연결
        val Logo = Intent(this, Activity_indexmain::class.java)
        binding.logobox.setOnClickListener{ startActivity(Logo)}

        //플레이 리스트 메뉴 링크 연결
        val PlayBtn = Intent(this, Activity_Playlist::class.java)
        binding.playBtn.setOnClickListener{ startActivity(PlayBtn)}

        //햄버거 메뉴 링크 연결
        val hamMenu = Intent(this, Activity_hambuger::class.java)
        binding.head3.setOnClickListener{ startActivity(hamMenu)}

        //메뉴 Section 링크 연결
        val productmore1 = Intent(this, Activity_product::class.java)
        binding.moreBtn.setOnClickListener{ startActivity(productmore1)}

        val productmore2 = Intent(this, Activity_product::class.java)
        binding.con3Slide.setOnClickListener{ startActivity(productmore2)}

        //리뷰 Section 링크 연결
        val contentreview1 = Intent(this, Activity_review::class.java)
        binding.reviewcon.setOnClickListener{ startActivity(contentreview1)}

        val contentreview2 = Intent(this, Activity_review::class.java)
        binding.button4.setOnClickListener{ startActivity(contentreview2)}

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