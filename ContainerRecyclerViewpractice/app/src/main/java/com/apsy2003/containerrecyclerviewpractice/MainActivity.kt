package com.apsy2003.containerrecyclerviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.apsy2003.containerrecyclerviewpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data:MutableList<Memo> = loadData()
        var adapter = CustomAdapter()
        adapter.listData =data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }


    fun loadData(): MutableList<Memo>{
        val data:MutableList<Memo> = mutableListOf()
        for(no in 1..100){
            val title = "안드로이드 예제 연습 중 ${no}"
            val date = System.currentTimeMillis()

            var memo = Memo(no, title, date)
            data.add(memo)
        }
        return data
    }
}