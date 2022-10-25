package com.apsy2003.harusamki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apsy2003.harusamki.databinding.ActivityCommunityBinding
import com.google.android.material.tabs.TabLayoutMediator

class Activity_Community : AppCompatActivity() {

    val binding by lazy{ActivityCommunityBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentList = listOf(fragment_One(), Fragment_Two(), Fragment_Tree())
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.ComVeiwPager.adapter = adapter

        val tabTitles = listOf<String>("최신순","인기순","찜한목록")
        TabLayoutMediator(binding.ComtabLayout, binding.ComVeiwPager){tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        val intent = Intent(this, Activity_indexmain::class.java)
        binding.backBtn.setOnClickListener{ startActivity(intent)}


        val intent3 = Intent(this, Activity_indexmain::class.java)
        binding.fmenu3.setOnClickListener{ startActivity(intent3)}
    }
}